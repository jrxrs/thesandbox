# Docker

Don't forget that in Docker, Containers are just running versions of Images.

## Simple Commands

```bash
docker pull <image_name>
```
Pull an image down from the container registry. **Note**: images pulled from a container registry are stored under ```/var/lib/docker/image/overlay2/imagedb/content/sha256```, the SHAs you see in this folder are the ones displayed in the output of ```docker images``` (see below). Also see ```docker inspect```.

```bash
docker images
```
List all of your local images.

```bash
docker inspect <image_name>
```
This command prints the JSON file which describes a container image. This is a lightweight version of cat'ing the files under ```/var/lib/docker/image/overlay2/imagedb/content/sha256``` mentioned above. This command will also show you the IP Address of the container.

```bash
docker build --tag <tag_name>
```
Build a docker image from a Dockerfile and annotate it with the specified tag name.

```bash
docker history <tag_name>
```
Show what happened to your image while it was being built (including information about the size of the image at each layer/stage).

```bash
docker run -it <image_name> /bin/bash
```
This runs a container and attaches to stdin, /bin/bash is the processed which is opened and ```-it``` is the interactive flag.
**Note**: to detach from a container without stopping it press: CTRL+P followed by CTRL+Q, then can then use the ```attach``` command to re-attach later.

```bash
docker ps
```
Lists the currently running containers. Add ```-a``` to list containers which have been stopped.

```bash
docker start <container_identifier>
```
If you wanted to start one of the stopped containers list above then use the command above.

```bash
docker attach <container_identifier>
```
The ```start``` command will restart your container but it will not attach to it automatically like ```run -it``` did, so use ```attach``` instead. Note that you an use either the container ID or the container name as an argument here.

```bash
docker stop <container_identifier>
```
Stop a running container.

```bash
docker rm <container_identifier>
```
Clean up any stopped containers by removing them.

```bash
docker container prune -f
```
Purge all stopped containers.

```bash
docker rmi <image_name>
```
Delete a docker image that you've built locally or pulled from a container registry.

```bash
docker image prune -af
```
Purge all docker images that you've previously deleted.

```bash
docker volume ls
```
List all of your persistent volumes.

```bash
docker volume inspect <volume_name>
```
View the location of a volume you have added to a container (note: Docker manages these volumes for you).

```bash
docker tag <original_repository>:<tag> <new_repository>:<new_tag>
```
This applies a new tag to an existing image, note that the repository names can be the same if you like. The resulting image will have the same IMAGE ID as the original image just a different set of tags. Note that you can just include the desired tag name in the build command e.g. ```docker build -t tag_demo:v2 .```

```bash
docker push ...
```
Used to push an image to a container registry.

```bash
docker info
```
This will output system-wide information about Docker.

## ```run```

* ```-it``` - iteractive mode with tty mode (making it act like a standard terminal)
* ```-d``` - detach - run the container in the background but print its' ID
* ```rm``` - remove - automatically delete a container as soon as it is stopped
* ```--name``` - name - give the container a name (if you don't specify one then Docker will give the container a random name for you)
* ```-p 8080:8080``` - port - configure port forwarding, this basically says map port 8080 from the docker host through to port 8080 of this container, i.e. ```host_port:container_port```
* ```-P``` - Publish all exposed ports to random ports
* ```-v``` - volume - mount a volume to your contianer, e.g. ```-v ${PWD}:/myvol``` would share a folder on your host machine with the container
* ```--mount type=volume,src="logs",dst=/logs``` - mount a volume to your container
* ```--mount type=bind,src="/var/demo/logs",dst=/logs``` - mount a bind volume to your container
* ```--mount type=tmpfs,dst=/logs``` - mount a tmpfs volume to your container (everything is in-memory here so there is no source required)
* ```-c "commands"``` - a list of commands to be executed inside your container
* ```-w <dir>``` - set the working directory, the allows you to omit leading paths from your volume, e.g. 
  * ```docker run --rm -v ${PWD}:/files klutchell/rar a /files/myrar.rar /files/myfile.txt``` can become
  * ```docker run --rm -v ${PWD}:/files -w files klutchell/rar a myrar.rar myfile.txt```
* ```--network=?``` - specify the network you would like your container to join

## Running Commands without elevating to root permissions

1. Verify the docker group exists by searching for it in the groups file: ```grep docker /etc/group````
2. If you don't see a line beginning with "docker:", you will need to add the group yourself by entering: ```sudo groupadd docker```
3. Add your user to the docker group: ```sudo gpasswd -a $USER docker```
4. You can login again to have your groups updated by entering: ```newgrp docker```
5. Verify that your user can successfully issue Docker commands by entering: ```docker info```
*Note*: if you don't see the system-wide Docker information, you may need to restart the Docker daemon by entering ```sudo systemctl restart docker```

## Docker Namespaces and Control Groups

* The pid namespace: Process isolation
* The net namespace: Managing network interfaces
* The ipc namespace: Managing acces to IPC resources (IPC: InterProcess Communication)
* The mnt namespace: Managing filesystem mount points
* The uts namespace: Isolating kernal and version identifiers (UTS: Unit Timesharing System)

These namespaces are how Docker archives high levels of process isolation. It also uses Control Groups to limit how much of a system a particular process can use, this prevents one process hogging everything, the groups are:

* Resource limiting: groups can be set to not exceed a configured memory limit
* Prioritisation: some groups may get a larger share of CPU utilisation or disk I/O throughput
* Accounting: measures a group's resource usage
* Control: freezing groups of processes

## UnionFS

The Union Filesystem is how Docker keeps images slime and lightweight, each image starts with a base image into which changes are merged, this prevents duplication etc. 
UnionFS also allows Read/Write control so that braches can be read-only or read-write. 

## Installation

I used the following guide to install Docker on CentOS: https://docs.docker.com/engine/install/centos/

## The Dockerfile

Reference: https://docs.docker.com/engine/reference/builder/

## Dockerfile Alternatives

Instead of using the Dockerfile to create an image it's also possible to use the ```commit``` command to modify an exiting image and do something like install python. An example of doing this would be running the following commands (note that you must be the root user):

```bash
sudo -i
docker run -it ubuntu /bin/bash
which python
apt update
apt full-upgrade
apt install python
which python
python --version
exit
# You should now be back on your VM, still as root
docker ps -a
docker commit --change='CMD ["python", "-c", "import this"]' <CONTAINER_ID> <TAG_NAME>
```

Note the ```--change``` argument passed to the ```commit``` command, this alters the default command that is run when the container starts (originally it was ```/bin/bash``` for ubuntu), it is also possble to change other properties of the image at this time.

## Port Mapping

Ports can be exposed in the Dockerfile used to build your image using the ```EXPOSE``` command, e.g. ```EXPOSE 8080```.
When running a container from an image which has a port exposed you can use the ```-P``` flag to ```docker run``` in order to bind all exposed ports to a random port on your host, executing ```docker ps -a``` will tell you which port was chosen (if you used ```-P / --publish-all```), see ```-p``` above if you want to specifiy exactly which port to bind on the localhost.

## Networking

Docker has 3 default networks which can be viewed with the ```docker network ls``` command, e.g.

```bash
[root@localhost ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
7fb140cb2444   bridge    bridge    local
4dda932d2c4b   host      host      local
f8347dc1cb1a   none      null      local
```

### The bridge network
If you start a container but do not specify a network then the ```bridge``` network will be used. While using the ```bridge``` network all containers can see each other as well as reach out to the internet. Customising the default networks isn't possible but you can create you own networks if you require customisation. 

### The host network
The host network runs with no isolation between the localhost and the containers, therefore if you start a container which exposes port 8080 then it will bind to port 8080 on your localhost. In order to specify the network use the ```--network=host``` flag to the ```docker run``` command. If you ```docker inspect``` a container which was started on the ```host``` network then you will see that it does not have an IP Address.

### The none network
Using the none network will mean that your container doesn't have any network connectivty at all (just the loopback interface), neither to other containers or the outside world.

## Volumes

Volumes are one of the three mechanisms that Docker provides to allow for persistent stoage (the others are Bind Mounts and In-memory Storage (tmpfs)).

Volumes are the preferred way to persist data in Docker containers and services. Some use cases for volumes include:
* Sharing data among multiple running containers. If you don't explicitly create it, a volume is created the first time it is mounted into a container. When that container stops or is removed, the volume still exists. Multiple containers can mount the same volume simultaneously, either read-write or read-only. Volumes are only removed when you explicitly remove them.
* When the Docker host is not guaranteed to have a given directory or file structure. Volumes help you decouple the configuration of the Docker host from the container runtime.
* When you want to store your container's data on a remote host or a cloud provider, rather than locally.
* When you need to be able to back up, restore, or migrate data from one Docker host to another, volumes are a better choice. You can stop containers using the volume, then back up the volume's directory (such as ```/var/lib/docker/volumes/<volume-name>```).

To mount a volume in your container use the ```--mount``` option to run (see ```run``` above).

You can view all your volumes using the ```docker volume ls``` command (see above).

### Bind Mounts
In general, you should use volumes where possible. Bind mounts are appropriate for the following types of use case:
* Sharing configuration files from the host machine to containers. This is how Docker provides DNS resolution to containers by default, by mounting ```/etc/resolv.conf``` from the host machine into each container.
* Sharing source code or build artifacts between a development environment on the Docker host and a container. For instance, you may mount a Maven ```target/``` directory into a container, and each time you build the Maven project on the Docker host, the container gets access to the rebuilt artifacts. If you use Docker for development this way, your production Dockerfile would copy the production-ready artifacts directly into the image, rather than relying on a bind mount.
* When the file or directory structure of the Docker host is guaranteed to be consistent with the bind mounts the containers require.

To mount a bind volume in your container use the ```--mount``` option to run (see ```run``` above).

### tmpfs Mounts
```tmpfs``` mounts are best used for cases when you do not want the data to persist either on the host machine or within the container. This may be for security reasons or to protect the performance of the container when your application needs to write a large volume of non-persistent state data. This type of volume doesn't even persist between invocations of the same container, e.g. run a new container, detach and stop it, then restart and reattach - in this case the tmpfs volume will have been cleaned up when you stopped so you'll start from scratch again when you restart. This is great for sensitive things like passwords or access tokens etc.

To mount a tmpfs volume in your container use the ```--mount``` option to run (see ```run``` above).

## Tagging

The ```latest``` is always implied if you don't specify a tag.

In Docker an image can have more than one tag, e.g. a version number and a version name like ubuntu does. 
Docker has a couple of type of tag, in the ```build``` command you can specify a ```-t <tag_name>``` to label your new image, if you don't specify a version then it will default to ```latest``` as above. You can then apply further tags to that image using the ```docker tag``` command (see above).

## Pushing an Image

You can push a docker image using the ```docker push``` command. By default you will push to Docker Hub (for which you'll need a account set up).

# Docker Compose

Compose is used to help manage orchestration of multiple containers.

Compose files are written in YAML and there is a little compleity around which version of the Compose file fomrat will work with which version of the Dcoker Engine, you can find a compatibilty matrix at http://docs.docker.com/compose/compose-file

# Docker Swarm

Docker Swarm allows you to run container services across multiple Docket hosts.

## Networking
The networking requirements in a swarm are much more complex than using a single Docker host. Services need to communicate with one another and the replicas of the service can be spread across multiple nodes. Fortunately, Docker includes a network driver that makes multi-host networking reliable, secure, and a breeze to set up.

### Overlay Networks
The driver I'm referring to is the overlay network driver. With the overlay driver a multi-host networking in a swarm is natively supported. There is no need to perform any external configuration. You can attach a service to one or more overlay networks, in the same way you would attach a container to one or more user-defined networks when not running in swarm mode.
Overlay networks only apply to swarm services and can't be connected to by containers that aren't part of a swarm service. Managers automatically extend overlay networks to nodes that run tasks requiring access to a given overlay network.

### Network isolation and firewalls
It's a good time to review Docker network isolation and firewall rules. These rules apply to overlay networks just as they do for bridge networks.
Containers within a Docker network are permitted access on all ports of containers in the same network.
Access is denied between containers that don't share a common network.
Traffic originating inside of a Docker network and not destined for a Docker host is permitted. For example, access to the internet. However, any network infrastructure outside of Docker may still deny the traffic.
Ingress traffic, or traffic coming into a Docker network, is denied by default. Ports must be published in order to grant access form outside of Docker.

### Service Discovery
With services distributed across multiple nodes, a service discovery mechanism is required in order to connect to the nodes running tasks for a service. Swarm mode has an integrated service discovery. It is based upon the domain name system (DNS). The DNS is internal to Docker and implemented in the Docker Engine. It is used for resolving names to IP addresses.

Actually, the same service discovery system is used when not running in swarm mode. Service discovery in Docker is scoped to a network. When you are in swarm mode, the network can be an overlay spanning multiple hosts. But the same internal DNS system is used. All nodes in a network store corresponding DNS records for the network. Only service replicas in the network can resolve other services and replicas in the network by name.

### Internal Load balancing
There are some unique service discovery considerations for Swarm mode. Each individual task is discoverable with a name to IP mapping in the internal DNS. But because services can be replicated across multiple nodes, which IP address should a service name request resolve to? Docker assigns a service a single virtual IP (VIP) address, by default. Requests for the virtual IP address are automatically load balanced across all healthy tasks spread across the overlay network. By using a virtual IP, Docker can manage the load balancing allowing clients to interact with a single IP address without considering load balancing. It also makes the service more resilient since the service can scale and tasks can change the nodes that they are scheduled on but clients are sheltered from the changes.

### Internal load balancing example
To illustrate how service discover and load balancing work in swarm mode, consider two services deployed in a swarm service A and service B. Service A has a single replica while service B has two replicas. When service A makes a request for service B by name, the virtual IP of service B is resolved by the DNS server. Service A uses the virtual IP to make a request for service B. Using support for ip virtual servers (IPVS) the request for the virtual IP address is routed to one of the two nodes running service B tasks.

### DNS Round Robin
Besides the default virtual IP, you can configure load balancing using DNS round robin (DNS RR). You can configure the load balancing on a per service basis. When DNS round robin is used, the Docker Engine's DNS server resolves a service name to individual task IP addresses by cycling through the list of IP addresses of node's running a task in the service. If you need more control over load balancing than a virtual IP can give you, DNS round robin should be used for integrating your own external load balancer.

### External Access
We've covered access to services within a Docker network, but what about accessing a service from the outside? With a single Docker host, you would publish a container port on the host to permit access to a container. Similar functionality is still available in swarm. But there are actually two modes for publishing ports in swarm.

### Host mode
The first is the same as you would expect when publishing a port when not running in swarm mode. The container port is published on the host that is running the task for a service. This mode is referred to as host mode service publishing. You need to be careful with specifying a host port in host mode. If you have more tasks than available hosts, tasks will fail to run because the host port can only be bound to one task. You can omit a host port to allow Docker to assign an available port number in the default port range of 30000-23767. However, this can make it more difficult to work. Also, there isn't load balancing unless you configure it externally. Obviously, that is useful when you don't want load balancing, but what about when you do?

### Ingress mode
Because services can be replicated and tasks can be rescheduled onto different nodes as the state of the swarm changes, it is useful to have the option to load balance a published port across all tasks of a service. This is referred to as ingress mode service publishing. For convenience, all nodes in the swarm publish the port. This is different from host mode where a port is only published if the node is running a task for the service. In ingress mode, requests are round robin load balanced across the healthy instances of the service's tasks regardless of the node that receives the request.

Ingress mode is the default service publishing mode. It's ideal when you have multiple replicas of a service and need to load balance between them. Host mode publishing is useful when you have an external service discovery service and potentially for global services where one task for a service runs on each node. For example, a global service that monitors each node's health shouldn't be load balanced since you want to get the status of a specific node.

### Routing Mesh
At this point, you might be wondering how ingress mode publishing work. The magic happens in what is called the routing mesh. The routing mesh combines two of the swarm components that we discussed earlier: an overlay network, and a service virtual IP.

When you initialize a swarm, the manager creates an overlay network named ingress. Every node that joins the swarm is in the ingress network. The sole purpose of the ingress network is to transport traffic from external clients that is destined to published service ports to the service inside the swarm.

When a node receives an external request on the ingress network the node resolves the service name to a virtual IP address. This process is carried out using the same internal DNS server as we discussed in the internal load balancing. The IP virtual server then load balances the request to a service replica over the ingress network.

Because every node is in the ingress network, every node can resolve the external requests can handle the external requests. The nodes need to have a couple of ports open for all of this magic to work:
o Port 7946 for both TCP and UDP protocols to enable container network discovery.
o Port 4789 for the UDP protocol to enable the container ingress network.

It's worth mentioning that you could add an external load balancer on top of the load balancing provided by the routing mesh. For example, if you have nodes running in the cloud, you can have the nodes in a private subnet so they aren't directly accessible from the internet. You could provision a cloud load balancer to handle requests from the internet and load balance them across nodes in the swarm. The swarm nodes then load balance again across the nodes running tasks for the service.

As a final note on the routing mesh, if you are planning to use the routing mesh on Windows, you need to be running version 17.09 or greater.

### docker_gwbridge
Besides the ingress network, Docker also creates a second network when running in swarm mode called docker_gwbridge. The docker_gwbridge is a virtual bridge that connects the overlay networks (including the ingress network) to an individual Docker daemon's physical network. This interface provides default gateway functionality for all containers attached to the network. Docker creates it automatically when you initialize a swarm or join a Docker host to a swarm, but it is not a Docker device. It exists in the kernel of the Docker host. You can see it if you list the network interfaces on your host.

### Recap
There was quite a few topics related to networking in swarm mode. Let's recap the main points:
" Swarm mode includes a new type of Docker network, the overlay network. Overlay networks make it easy to use multi-host networking in a swarm.
" The same internal DNS service discovery mechanism used when not running in swarm mode is used in swarm mode. The internal DNS naturally extends to multi-host networks.
" The services in a swarm can be load balanced by using a virtual IP address or by DNS round robin.
" External access to the swarm is made possible by publishing ports. There are two modes for publishing in swarm mode: host and ingress.
o In host mode each service replica publishes it's container port on the host. No load balancing is used.
o In ingress mode, every node in the swarm publishes the port and requests are load balanced across all the replicas of a service. Any node can handle requests for the service even if the node doesn't have a replica of the service itself.
" Ingress mode is made possible by the swarm routing mesh which uses two default swarm networks: the ingress overlay network and docker_gwbridge network

## Orchestration

Swarm mode is made to be familiar to single host Docker users. When you deploy a service, it is similar to running a container. You can specify an image, volumes, networks, published ports, After all, service tasks ultimately run containers. But there are container orchestration features of swarm mode that are unique to running services in a swarm.

Agenda
We'll look at the following orchestration features of swarm mode:
" (Service placement) Which nodes service tasks are placed on
" (Update behavior) how service updates are rolled out, and
" (Rollback behavior) how services can be rolled back to a previous version.


### Service placement
As we've discussed, services can declare a set number of replicas as a replicated service or can be started on every worker node in a cluster as a global service. For replicated services, decisions need to be made by swarm managers for where service tasks will be scheduled, or where the service will be placed. A replicated service's tasks will be spread across nodes by default. That is to promote high availability in case a node fails. But there are three ways that you can influence where a service is placed:
1. CPU and Memory reservations
2. Placement constraints
3. Placement preferences
You can specify each at service creation time. Global services can also be restricted to a subset of nodes with these conditions. Although a node will never have more than one task for a global service. Let's take a closer look at each.

### CPU and Memory reservations
Similar to running individual containers, you can declare CPU and memory reservations for services. Each service task can only be scheduled on a node that has enough available CPU and memory to meet the given reservations. Any tasks that remain stay in a pending state until a node with sufficient resources becomes available. Global services will only run on nodes that meet a given resource reservation.

Setting sufficient memory reservations for services is important when there isn't an abundance of CPU and memory available for the applications you are running. If services attempt to use more memory than is available, the container or Docker daemon could get killed by the out of memory or OOM killer.

### Placement constraints
Placement constraints allow you to restrict the placement of tasks by providing equality and inequality conditions. The conditions compare node attributes to a string value. There are a few built-in attributes for each node
1. node.id matches the ID of a node
2. node.hostname matches a node's hostname
3. node.role matches a node's role, either manager or worker

You can also define your own labels. You can configure labels on a Docker engine or on a node. Engine labels are usually used to indicate things like operating system, system architecture, available drivers. An example is engine.labels.operatingsystem and values could be Ubuntu 14.04 or Windows Server 2016. Node labels are added by Swarm administrators for operational purposes. Node labels can indicate they type of application a node is intended to run, the datacenter location a node is in, the server rack a node is in, et cetera. An example is node.labels.datacenter and values could be north, south, east, or west.

When you provide multiple placement constraints for a service, all constraints must be satisfied by a node in order to be scheduled a service task. If resource reservations are also provided, all constraints and resource reservations must be met. This is true for replicated and global services.


### Placement Preference
Placement preference is not required as was the case for resource reservations and placement constraints. Instead, placement preferences influence how tasks are distributed across appropriate nodes. Currently the only distribution option is spread which will evenly spread tasks.
Labels are again used as the attribute for spreading tasks. For example, assume every node in a swarm has a datacenter label with either east or west as the value. Using the datacenter label and the spread placement preference, half of the tasks will be scheduled on east datacenter nodes and the other half on west datacenter nodes.

Multiple placement preferences can be specified. In this case a hierarchy of preferences is created. For example, if the first preference is datacenter and the second Is server-rack, tasks will be evenly spread across nodes in each datacenter, and within each datacenter tasks are spread evenly across racks.

Nodes that are missing a placement preference label are included in the spread and receive tasks in proportion equal to all other label values. They are treated as the group having the null value for the label. Placement preferences are ignored by global services.

That's all that there is to influencing service placement in swarm.

### Update Behavior
You can also configure the way that swarm applies updates to services. Swarm supports rolling updates where a fixed number of replicas are updated at a time until all service replicas have been updated.

You can configure several update parameters:
1. Update parallelism, which sets the number of tasks the scheduler updates at a time
2. Update delay, which sets the amount of time between updating sets of tasks, and
3. Update failure action, which can be set to pause, continue or automatically rollback if an update fails. The default is to pause.
These are the three main settings. There are also settings to configure what qualifies as failure. You can set a ratio for the number of failed task updates to tolerate before failing a service update, and set the frequency for monitoring for a failure.

These parameters give you some flexibility in how aggressively or conservatively you roll out an update to the swarm.

### Rolling Back Updates
Docker swarm keeps track of the previous configuration for services. This allows you to rollback manually at any time or automatically when an update fails, as we discussed.

The same options available for configuring update behavior are available separately for configuring rollbacks. For example, rollback parallelism sets how many nodes to roll back at a time.

### Recap
In this lesson, we saw how you can influence the nodes that swarm schedules services on by using resource reservations, placement constraints, and placement preferences. Resource reservations and placement constraints must be satisfied, while placement preferences won't prevent a task from being scheduled. We also discussed how rolling updates and rollbacks can be configured in Swarm. Updates and rollbacks share the same available configuration options.

## Consistency

Consistency is an important consideration for any distributed system. In this lesson, we'll look at the consistency model of swarm mode and how it can impact how you operate a swarm.

Agenda
To kick things off we'll discuss:
" (Consistency) the consistency problem and
" (Raft) how swarm mode goes about solving it, in particular the Raft Consensus algorithm.
" (Tradeoffs) We'll cover just what you need to know of Raft to understand key tradeoffs that you should consider when deciding on the composition of your swarm.
" (Raft Logs) Lastly, we'll talk about the raft logs where the cluster state is stored.

### Consistency
We have seen that swarm mode can include several manager and worker nodes in a swarm. This provides fault tolerance if a node were to go down and ensures services are highly available. But with multiple managers, how does swarm make decisions regarding the state of the cluster? Do nodes in the swarm share a consistent view of the cluster or could one node have a different view than the other? And if so, for how long? These questions all touch on the issue of consistency.

In swarm mode, managers all share a consistent internal state of the entire swarm. This avoids any potential issues that could arise if managers were allowed to eventually converge to a shared state. Workers, on the other hand, do not share a view of the entire swarm. That is exclusively a manager responsibility.

The managers maintain a consistent view of the state of the cluster by using a consensus algorithm. There are several consensus algorithms to choose from and the implementation details are outside the scope of this course. But the consensus algorithm has an impact on how the swarm operates. We'll look at the basics of swarm modes consensus algorithm so we can understand the implications in operating a swarm.

### Raft Consensus
The consensus algorithm used by managers to maintain a consistent view of the state of the cluster is called Raft. Raft achieves consensus by electing one manager as the leader. The elected leader makes all of the decisions for changing the state of the cluster to bring it to the desired state. For example, the leader accepts new service requests and service updates and also decides how to schedule tasks.

In order to maintain a consistent view across the managers, the decisions aren't acted upon until a majority of managers agree on the proposed changes to the cluster. A manager "agrees" simply by receiving a proposed change and acknowledging they received it. When the leader is certain a majority of managers have received the proposed change, the change can be implemented. In this context, the majority of managers are referred to as a quorum.

The reason why a quorum is enough to proceed is because Raft limits how many managers failures it can tolerate. If you have N managers in a swarm, Raft allows for (N-1)/2 failures. In the case of a three manager swarm, that means 3 minus 1 divided by two is one, so one manager can fail and the swarm can continue to operate as usual. If two managers were to fail, the cluster state would freeze until a quorum of managers again became available. In the absence of a quorum, currently running services will continue to run but no new scheduling decisions take place.

Regarding leader elections, when a swarm is initialized the first manager is automatically the leader. If the currently elected leader fails or voluntarily steps down, say to perform system updates, an election between remaining manager nodes takes place. Until a newly elected leader is chosen, the cluster state is frozen.

### Manager Tradeoffs
After that overview of Raft consensus, you might be tempted to add a lot of managers to your swarm. The more managers, the more failures your swarm can tolerate and remain fully operational. Although, that is true, the more managers that are in the swarm also increases the amount of managerial traffic required for maintaining a consistent view of the cluster and the amount of time it takes to achieve reach consensus with every state change. Although increasing managers does increase fault-tolerance it generally decreases performance and scalability.

There are some general rules for setting the number of managers:
You should usually have an odd number of managers. Having an even number of managers doesn't improve the fault tolerance compared to having one less manager and increases communication overhead.
A single manager swarm is acceptable for development and test swarms. Because a single manager swarm can't tolerate any failures, it is not something you should use in production.
A three manager swarm can tolerate one failure, while a five manager swarm can tolerate two.
Docker recommends a maximum of seven managers which can tolerate three manager failures. Above seven has too much of an impact on performance to be beneficial.

However many managers you settle on, you will want to distribute them across availability zones to maintain a fully operational swarm in the event of a datacenter outage. Docker recommends distributing across at least three availability zones in production.

### Working Manager
There is another tradeoff when considering managers in a swarm. Have you heard the bad joke that goes "Don't stand around doing nothing. People will think you're the boss." In swarm mode, you need to consider whether or not you let the boss, or the managers, do work. By default managers perform worker responsibilities, namely running tasks. But that has more to do with enabling single node swarms than anything.

Because managers participate in the Raft consensus process, it can be detrimental to the performance of the swarm if managers are overly utilized. You can use conservative resource reservations to make sure that managers won't become starved for resources. To be on the safe side, you can also prevent any work from being scheduled on manager nodes by draining them. Draining essentially removes any tasks currently on a node and preventing new tasks from being scheduled to it.

### Worker Node Tradeoffs?
You might be wondering if there are any tradeoffs to consider when adding worker nodes to a swarm. There really isn't much to worry about in the case of adding more worker nodes. More workers give you more capacity for running services and improves service fault tolerance. More workers don't affect the manager's raft consensus process so the swarm performance isn't harmed.

Workers actually do participate in a consensus process. To exchange overlay network information nodes participate in a weakly-consistent, highly scalable gossip protocol called SWIM. The details are outside the scope of this course and the performance implications are negligible. The protocol is an example of an eventually consistent model where the network state is allowed to differ between nodes but eventually they converge on a consistent view.

### Raft logs
The last topic we'll discuss in this lesson is Raft logs. If you arrived at this lesson from a search for log rafts, I'm afraid you'll need to continue your search. The logs we're talking about are where the leader manager records the Raft consensus state changes, such as creating a new service or adding a new worker. These logs are what get shared with other managers to establish a quorum.

The Raft logs are persisted to disk. The logs are stored in the raft subdirectory of your Docker swarm data directory. This is /var/lib/docker/swarm on Linux by default. As part of a disaster recovery strategy, you can back up a swarm cluster by backing up the entire swarm directory which includes certificates and other files in addition to the raft change logs in the raft subdirectory. You can restore a new swarm from a backup by replacing the directory swarm directory with the backed-up copy.

### Recap
That's everything for this lesson. We started by understanding how swarm mode solves consistency challenges by electing a leader manager and ensuring a majority of managers acknowledge swarm changes. This strategy comes from the Raft consensus algorithm. We understood the tradeoffs between fault tolerance and performance when choosing the number of managers in a swarm, as well as whether or not managers should do work. We finished by discussing the raft logs which are persisted on disk and record all the changes the leader makes to a swarm.
