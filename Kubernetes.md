# Kubernetes

Kubernetes, often abbreviate as K8s, is an open-source container-orchestration tool designed to automate, deploying, scaling, and the operation of containerized applications.

Kubernetes was born out of Google's experience running workloads in production on their internal Borg cluster manager for well over a decade, it is designed to grow from tens, thousands, or even millions of containers. Organizations adopting Kubernetes increased their velocity by having the ability to release faster and recover faster with Kubernetes self healing mechanisms. Kubernetes is a distributed system. Multiple machines are configured to form a cluster. Machines may be a mix of physical and virtual and they may exist on-prem or in cloud infrastructure each with their own unique hardware configurations.

## Offical Documentation

* https://kubernetes.io/docs/home/
* https://kubernetes.io/docs/reference/glossary/?all=true

### Other Links
* https://blog.arima.eu/en/2020/04/28/examen-ckad
* https://dzone.com/articles/my-experience-with-the-cka-exam

## Alternativers to K8s

### Data Center Operating System (DCOS)
DCOS or Data Center Operating System is similar to Kubernetes in many ways DCOS pools compute resources into a uniform task pool, but the big difference here is that DCOS targets many different types of workloads including, but not limited to, containerized applications. This makes DCOS attractive to organizations which are not using containers for all of their applications. DCOS also includes a Package Manager to easily deploy it to his systems like, Kafka or Spark. You can even run Kubernetes on DCOS given its flexibility for different types of workloads.

### Amazon ECS
Amazon ECS, or the Elastic Container Service is AWS' ability to orchestrate containers. ECS allows you to create pools of compute resources and uses API calls to orchestrate containers across them. Compute resources are EC2 instances that you can manage yourself or let AWS manage them with AWS Fargate. It's only available inside of AWS and generally, less feature compared to other open source tools. So it may be useful for those of you who are deep into the AWS ecosystem.

### Docker Swarm
Docker Swarm Mode is the official Docker solution for orchestrating containers across a cluster of machines. Docker Swarm Mode builds a cluster from multiple Docker hosts and distributes containers across them. It shows a similar feature set with Kubernetes or DCOS. Docker Swarm Mode works natively with the docker command. This means that associated tools like Docker Compose can target Swarm Mode clusters without any changes.

Docker Enterprise Edition leverages Swarm Mode to manage an enterprise-grade cluster. And Docker also provides full support for Kubernetes if you want to start out with Swarm and later swap over to Kubernetes. So if you're not already fixed on you using Kubernetes I would recommend that you conduct your own research to understand each tool and its trade-offs. Cloud Academy has content for each option to help you make the right decision.

## Kubernetes Deployments in The Cloud
For your production workloads, you want clusters with multiple nodes to take advantage of horizontal scaling and to tolerate node failures. To decide what solution works best for you, you need to ask several key questions including, "How much control do you want over the cluster versus the amount of effort you are willing to invest in maintaining it?"

### Fully Managed
Fully-managed solutions free you from routine maintenance but often lag the latest Kubernetes releases by a couple of version numbers for consistency. New versions of Kubernetes are released every three months. Examples of fully-managed Kubernetes as a service solutions include Amazon Elastic Kubernetes Service or EKS, Azure Kubernetes Service or AKS, and Google Kubernetes Engine or GKE.

### Full Control
To have full control over your cluster, you should check out kubespray, kops, and kubeadm. The next question is, "Do you already have investment into and expertise with a particular cloud provider?" Cloud provider's managed Kubernetes services integrate tightly with other services in their cloud. For example, how identity and access management is performed. There will be a lot less friction to staying close to what you already know.

### Enterprise Support, Vendor Lock-in and OS Considerations
1. "Do you need enterprise support?" Several vendors offer enterprise support and additional features on top of Kubernetes. These can include OpenShift by RedHat, Pivotal Container Service, or Rancher.
1. "Are you concerned about vendor lock-in?" If you are, you should focus on open source solutions, like kubespray and Rancher that can deploy Kubernetes clusters to a wide variety of platforms.
1. "Do you want to run Linux containers, Windows containers, or a mix? To support Linux containers, you need to ensure you have Linux nodes in your cluster. To support Windows containers, you need to ensure that you have Windows nodes in your cluster. Both Linux and Windows nodes can exist in the same cluster to support both types of containers.

## Architecture

### Cluster
The Kubernetes cluster is the highest level of abstraction to start with. Kubernetes clusters are composed of nodes and the term cluster refers to all of the machines collectively and can be thought of as the entire running system.

### Nodes
The machines in the cluster are referred to as nodes. A node may be a VM or a physical machine. Nodes are categorized as worker nodes or master nodes.

### Worker
Each worker node includes software to run containers managed by the Kubernetes control plane and the control plane runs on master nodes.

### Master
The control plane is a set of APIs and software that Kubernetes users interact with. These APIs and software are collectively referred to as master components.

### The Control Plane (Scheduler)
The control plane schedules containers onto nodes. So the term scheduling does not actually refer to time in this context. Think of it from a Kernel perspective the Kernel schedules processes onto CPU's according to multiple factors. Certain processes need more or less compute or may have different quality of service rules. Ultimately the scheduler does its best to ensure that every container runs. Scheduling in this case refers to the decision process of placing containers onto nodes in accordance with their declared compute requirements.

### Pods
In Kubernetes containers are grouped into Pods. Pods may include one or more containers but generally it is best practice to have just a single container per pod. All containers in a Pod run on the same node. And the Pod is actually the smallest building block in Kubernetes. More complex and useful abstractions sit on top of Pods.

### Services
Services, define networking rules for exposing Pods to other Pods or exposing Pods to the internet.

### Deployments
A deployment represents multiple replicas of a pod. Kubernetes uses deployments to manage the deployment configuration and changes to running Pods as well as horizontal scaling. These are fundamental terms you need to understand before we can move forward. Effectively Kubernetes Deployments control rollout and rollback of Pods.

### Namespace
A Namespace separates different Kubernetes resources. Namespaces may be used to isolate users, environments, or applications. You can also use Kubernetes' role-based authentication to manage users as access to resources in a given Namespace. Using Namespaces is a best practice.

## ```kubectl```
The ```kubectl``` command is the primary mechanism of interacting with a Kubernetes cluster, details of some of the fundamental commands are given below:

[Kubectl Cheatsheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)

### ```create```
```kubectl create``` creates new Kubernetes resources, e.g. Pods, Services, etc. You can create several resources using the built-in sub commands of create, or you can use resources specified in a file. The files are most commonly in YAML format and are referred to, as manifests.

#### Options
* ```-f <name_name>``` - the ```-f``` switch tells kubectl to create a resouce from the specified manifest file.

### ```delete```
```kubectl delete``` does the opposite of create, in that it deletes a particular resource. You can do the same with a file, with resources declared inside of it.

#### Options
* ```kubectl delete pod mypod``` - deletes the pod called "mypod".
* ```kubectl delete -f <file_name>``` - deletes all the resources declared in the manifest file.

### ```get```
```kubectl get``` returns a list of all the resources for a specified type. 

#### Options
* ```kubectl get pods``` - lists all the pods in the current namespace.
* ```kubectl get services``` - lists all services in the current namespace.
* ```kubectl get namespace``` - list all the available namespaces.
* ```kubectl get nodes``` - list all the nodes in the cluster, e.g. master and worker nodes.
* ```kubectl get deployments``` - list all the deployments.

### ```describe ```
```kubectl describe``` is going to print detailed information about a particular resource or a list of resources. The "Events" section of the ```describe``` output can be very helpful for debugging. 

#### Options
* ```kubectl describe pod``` gives detailed information about all pods.
* ```kubectl describe pod server``` gives detailed information about the pod named server.
* ```kubectl describe service webserver``` gives detailed information about the pod named server.
* ```kubectl describe nodes | grep -i address -A 1``` - used to get the node address for accessing a service (note that you might see multiple addresses here if you have multiple nodes in your cluster, but if you're using a NodePort service then it won't matter which node in the cluster you hit)

### ```logs```
```kubectl logs``` prints container logs for a particular pod or a specific container inside of a multi container pod.

## Pods
Let's look at the most basic manifest file for a Pod, see [1.1-basic_pod.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/1.1-basic_pod.yaml). This manifest declares a pod with one container that uses the latest Nginx image. All manifests have the same top level keys, ```apiVersion```, ```kind`, and ```metadata``` followed by the ```spec```.
* Kubernetes supports multiple ```apiVersion```s and version 1 is the core API version containing many of the most common resources such as pods and nodes.
* ```kind``` indicates what the resource is. 
* ```metadata``` then includes information relevant to the resource that can help identify resources. The minimum amount of metadata is a name which is set to ```"mypod"```. **Names must be unique within a Kubernetes name space**.
* ```spec``` is specification with a clear kind and must match what is expected by the defined API version. The spec is essentially where all of the meat goes.

### Exposing Ports
Just like in Docker, by default Kubernetes will not expose a port unless you explicitly tell it to do so. See [1.2-port_pod.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/1.2-port_pod.yaml) for an example, we can see the ports mapping is added and the container port field is set to 80 for HTTP. Kubernetes is also going to be using TCP as the protocol by default and will assign it an available host port automatically so that we don't need to declare anything more. Kubernetes can apply certain changes to different kinds of resources on the fly. Unfortunately, Kubernetes cannot update ports on a running pod so we need to delete the pod and recreate it. We're going to be running our kubectl delete pod my pod to delete this pod. You can also specify with the -f with referencing to the 1.1 file and Kubernetes will delete all of the resources declared in that file. 

However, even after exposing the port port on the container a request to that port will still not work, this is because the pod's IP is on the container network and this lab instance is not part of the container network. Note that if we sent the request from a container in a Kubernetes pod, the request would succeed since pods can communicate with all other pods by default.

### Labels
Labels are key value pairs that identify resource attributes. For example, the application tier, whether it's front end or back end or maybe a region such as US East or US West. In addition to providing meaningful and identifying information, labels are used to make selections in Kubernetes. For example, you could tell kubectl to get only resources in the US West region. So, our [1.3-labeled_pod.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/1.3-labeled_pod.yaml) manifest has a label added to identify the type of app that the pod is a part of. We're using an Nginx web server and the label value is web server. You could have multiple labels but one is enough in this example.

### Setting Resource Constraints
Kubernetes can schedule pods based on their resource requests. The pods that we've seen so far don't have any resource requests set which makes it easier to schedule them because the scheduler doesn't need to find nodes that have these requests for amounts of resources. It'll just throw them onto any node that isn't under pressure or starved of resources. However, these pods will be the first to be evicted if a node becomes under pressure and it needs to free up resources. This is called best effort quality of service (which was displayed in the "QOS Class" field of the ```describe``` output. Best effort pods can also create resource contention with other pods on the same node and usually it's a good idea to set resource requests. [1.4-resources_pod.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/1.4-resources_pod.yaml) sets a resource request and limit for the pod's container. **Request** sets the **minimum** required resources to schedule the pod onto a node and the **limit** is the **maximum** amount of resources you want the node to ever give the pod. You can set resource requests and limits for each container. There's also support for requesting amounts with local disk by using the ephemeral storage. If we delete and recreate our pod using this latest file it will switch to QOS Class: Guaranteed.

## Services
In the Pods section we saw that we could expose a port on a container but were unable to access it from outside the container network, to solve this problem we need to employ a Service. Services allow us to deal with pod failure or reallocation of a pod to a different node in the cluster. A service defines networking rules for accessing Pods in the cluster and from the internet. For example it is possible to declare a service to access a group of Pods using labels, the service receives a fixed IP address that can be use externally to reach it, Kubernetes then handles the internal routing (and load balacing) so that the resuest reaches a pod with the target lebel, e.g. webserver.

How do we achieve this in a manifest file? Let's consider [2.1-web_service.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/2.1-web_service.yaml),  our first three fields are set to the same as before. The ```kind``` is now Service, ```metadata``` uses the same ```label``` as the Pod since it is related to the same application. This isn't required but it is a good practice to stay organized. Now for the ```spec```, the ```selector``` is our important field. The selector defines the labels to match the Pods against. At this example of targets Pods with the ```app: webserver```, which will select the Pod that we've already created. Services must also define ```port``` mappings. So, this service targets Port 80. This is the value of the Pods' container port. Lastly, is the optional ```type```. This value defines actually how to expose the Service and we're gonna set it to ```NodePort```. NodePort allocates a port over this service on each node in the cluster. By doing this, you can send a request to any node in the cluster on the designated port and be able to reach that Service. The designated port will be chosen from the set of available ports on the nodes, unless you specify a NodePort as part of the specs ports. Usually it is better to let Kubernetes choose the NodePort from the available ports to avoid the chance that your specified port is already taken. That would cause the service to fail to create.

When you use ```kubectl get services```, kubectl will display the name, Cluster-IP, External-IP, Ports, and Age of each service:
* Cluster-IP is our private IP for each service. 
* External-IP is not available for NodePort services but if it were, then this would be the public IP for a service. 
* Note that the Ports column, Kubernetes will automatically allocate a Port in the Port range allocated for NodePorts which is commonly port numbers between 30,000 and 32,767.

We have seen that services allow us to expose Pods using a static address, even though the addresses of the underlying Pods may be changing. We also specifically used a NodePort service to gain access to the service from outside of the cluster on a static port that is reserved on each node in the cluster. This allowed us to access the service by sending a request to any of the nodes, just not the node that is running the Pod. There is more to say about Pods and services. We will use more complex application in the future to illustrate some of the remaining topics in the next couple of lessons. Think microservices will start by covering multi-container Pods, to continue on when you're ready.

## Multi-Container Pods
You can see an example of how to create a namespace in the [3.1-namespace.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/3.1-namespace.yaml) file. The  namespace is created, just like any other Kubernetes resource. Here is our Namespace manifest. Namespaces don't require a spec. The main part is the name which is set to microservices and is a good idea to label it as well. Everything in this Namespace will relate to the counter microservices app. It's important to **note** that the ```--namespace``` or ```-n``` option must be used to specify the namespace on all ```kubectl``` commands we run from now on, otherwise the default namespace will be used. 

We now move to look at creating a pod in this namespace, [3.2-multi_container.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/3.2-multi_container.yaml) will do this for us. You could actaully specify the nameapce inside the ```metadata``` of the pod, however this wouldn't be very portable as it would mean we couldn't override the namespace later at the command line. This manifest file contains some other interesting points, such as the ```imagePullPolicy``` tag, this is handy to use when you want to use the latest version of an image as Kuberentes will always pull the image whenever the Pod started, however this can introduce bugs, if a pod restarts and pulls the new latest version without you realizing it. Setting```imagePullPolicy: IfNotPresent``` will prevent this as Kubernetes will a version cached locally if available but generally speaking it is considered **best practice** to specify a specific tag rather than the latest. When specific tags are used, the default image pull behavior is, ```IfNotPresent```.

There is an obvious problem with the solution created here, namely that it will not scale well because Pods are our smallest union of work, Kubernetes can only scale out by increasing the number of Pods and not the containers inside of the Pod. If we want to scale out the application tier with the current design we have to also scale out all other containers proportionately. This means that there would be multiple Redis containers running, each would have their own copy of the counter. That's certainly not what we're gonna be going for. It is a much better approach, if we were able to scale each of these services independently. Breaking the application out into multiple Pods and connecting them with services is our ideal implementation. We'll walk through the design in next lesson but before moving on, it's worth noting that sometimes you do want each container in a Pod to scale proportionately. It comes down to how tightly coupled the containers are, and if it makes sense to be thinking of them as a single unit.

## Service Discovery
We will use services to help break down our monolithic pod into separate pods, one for each application tier. But how will the individual pods know how to communicate with pds from neighbouring tiers? For that we need a service discovery mechanism. 

There are two Service discovery mechanisms built into Kubernetes. The first are environment variables, and the second is DNS. Kubernetes will automatically inject environment variables into containers that provide the address to access services. The environment variables follow a naming convention so that all you need to know is the name of the service to access it. Kubernetes also constructs DNS records based on the service name and containers are automatically configured to clear the clusters, DNS, to discover those services. You'll see examples of both techniques in this lesson.

[4.1-namespace.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/4.1-namespace.yaml) will create a new namespace for us to work in. The [4.2-data_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/4.2-data_tier.yaml) manifest is starting to look a little more complex because it lists both a ```Service``` and a ```Pod``` within the same file, **note** these declarations must be divided by three dashes: ```---```. Also note that you can add comments in the YAML file using ```#```. If you do choose to specify multiple resources in the same manifest file, then Kubenetes will create each resource in the order it is listed in the file.

Next we will create the application tier using [4.3-app_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/4.3-app_tier.yaml), the most important feature of this file to grasp is how environment variables are injected by Kubernetes to facilitate the application tier communicating with the data tier, e.g. ```value: redis://$(DATA_TIER_SERVICE_HOST):$(DATA_TIER_SERVICE_PORT_REDIS)```, the comment in the file explain more about this. When using environment variables for service discovery, the service must be created before the pod in order to use environment variables for service discovery. That is, Kubernetes does not update the variables of running containers. They only get set at startup. The service must also be in the same namespace for the environment variables to be available.

Finally we'll look at creating the support tier with [4.4-support_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/4.4-support_tier.yaml). No Service is required for this tier, just the two containers to handle counting and polling. This time, instead of using environment variable for service discovery we will use DNS. Kubernetes will add a DNS A record for every service. The service DNS names follow the pattern of ```<service_name>.<service_namespace>```. In our example that is: ```app-tier.service-discovery```. However, if the service is in the same namespace, then you can simply only use the service name. The poller omits the namespace in this manifest. No need to convert hyphens to underscores, or use all caps when using DNS service discovery. The cluster DNS resolves the DNS name to the service IP address. You can get service port information using DNS SRV records, but that isn't something that we can use in the manifest file. So I'll have to either hard-code the port information or use the service port environment variable. The counter uses a hard-coded port, and the polar uses the port environment variable for illustration.

## Deployments
So far we have created pods directly but this is really cheating, you're not really supposed to create pods directly. Instead, a pod is really just a building block. They should be created via a higher level abstraction such as deployments. This way, Kubernetes can add on useful features and higher level concepts to make your life easier.

A deployment represents multiple replicas of a pod. Pods in their deployment are identical, and within a deployment's manifest, you embed a pod template that has the same fields as this pod spec that we have written before. The Kubernetes master components include a deployment controller that takes care of managing the deployment, including bring pods up and down to match the desired target state and updating pods.

Let's have a look at some example manifests, we'll start by declaring a new namespace in [5.1-namespace.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/5.1-namespace.yaml), consider that a deployment is a template for creating pods. A template is used to create replicas, and a replica is a copy of a pod. This allows applications scale by creating more replicas. If we compare [4.2-data_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/4.2-data_tier.yaml) with [5.2-data_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/5.2-data_tier.yaml) there are a few important things to note:

* ```apiVersion``` is now **apps**/v1, this is because apps have their own distinct API group
* The ```kind``` is now Deployment
* But the ```metadata``` remains identical
* The deployment ```spec``` contains deployment-specific settings and also a pod ```template```, which has exactly the same pod spec as 4.2 in it.
* In the deployment-specific section, the ```replicas``` key sets how many pods to create for this particular deployment. Kubernetes will keep this number of pods running. We set the value to one because there cannot be multiple Redis containers. We'll have one Redis pod. Note that one is actually the default number of replicas, so it isn't strictly required, but it does emphasize that a deployment manages a group of identical replicas.
* Just like we saw with services, deployments use label ```selector```s to group pods that are in the deployment. The match labels mapping should overlap with the labels declared in the pod template below, and kubectrl will complain if they don't overlap. The pod template metadata includes labels on the pods.

The same deployment specification can be added to the other layers of the application, i.e. [5.3-app_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/5.3-app_tier.yaml) and [5.4-support_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/5.4-support_tier.yaml).
