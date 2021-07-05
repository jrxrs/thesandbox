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

[Understanding Nodes, Pods, Containers and Clusters](https://faun.pub/understanding-nodes-pods-containers-and-clusters-778dbd56ade8)

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

It's vital to note that using the default namespace is discouraged, so a namespace should be used in alsmost every circumstance, all of the ```kubectl``` command below take a ```--namespace <name_space>``` and ```-n <name_space>``` command line option to allow you to specify which namespace you're querying for.

## ```kubectl```
The ```kubectl``` command is the primary mechanism of interacting with a Kubernetes cluster, details of some of the fundamental commands are given below:

[Kubectl Cheatsheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)

**Note**: ```kubectl``` will accept the singular or plural form of resource kinds. For example ```kubectl get pods``` and ```kubectl get pod``` are equivalent.

### ```create```
```kubectl create``` creates new Kubernetes resources, e.g. Pods, Services, etc. You can create several resources using the built-in sub commands of create, or you can use resources specified in a file. The files are most commonly in YAML format and are referred to, as manifests.

#### Options
* ```-f <name_name>``` - the ```-f``` switch tells kubectl to create a resouce from the specified manifest file (note that you can pass multiple ```-f``` switches on the command line or entire sub-directories).

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
* ```kubectl describe hpa``` - in this case ```hpa``` is shorthand for horizonal pod autoscalers and this will show us what the autoscaler is doing to meet our requirements behind the scenes.

### ```logs```
```kubectl logs``` prints container logs for a particular pod or a specific container inside of a multi container pod.

#### Options
* ```kubectl logs <resource> <image_name>

### ```scale```
```kubectl scale``` allows a resource to be scaled to a specifc number of replicas at runtime.

#### Options
* ```kubectl scale -n <namespace> deployments <deployment_name> --replicas=5``` - this scales the deployment named <deployment_name> to have 5 replicas without modifying the manifest for that deployment

### ```top```
```kubectl top``` displays runtime information, CPU usage and memory utilisation for a resource. Note that the m stands for milli, 1000 milli CPUs equals one CPU.

#### Options
* ```kubectl top pods``` - 

### ```apply```
```kubectl apply``` is like ```create``` but can be used to apply manifest chanegs to a resource which is already running.

### ```edit```
```kubectl edit``` is the equivalent of editing an manifest, e.g. updating the number of replicas, saving the file and then using ```kubectl apply``` to apply the changes. Note that the default editor is ```vi``` and when you use ```edit``` you're actually modifying the server side version of the manifest, not your local copy, as a result you'll see some additional fields that ```kubectl``` has defaulted for you.

### ```rollout```
```kubectl rollout <command> <resource_type> <resource_name>``` allows you to view and control rollouts as they occur. Rollouts are most commonly used with deployments.

#### Options
* ```kubectl rollout status <resource_type> <resource_name>``` prints details of the process of a rollout as it happens.
* ```kubectl rollout pause <resource_type> <resource_name>``` prints details of the process of a rollout as it happens. Pausing won't pause replicas that were created before the pausing, they will continue to progress to ready. However, there will be no new replicas created after the rollout is paused.
* ```kubectl rollout resume <resource_type> <resource_name>``` resume a paused rollout.
* ```kubectl rollout undo <resource_type> <resource_name>``` this will rollback to the previous revision
* ```kubectl rollout history <resource_type> <resource_name>``` will get a list of all versions allowing you to get a specific version and pass that in

### ```api-resources```
```kubectl api-resources``` prints a fulll list of all the short hand annotations that kubectl supports.

### ```exec```
```kubectl exec <target_resource> -it -- <command>``` The ```exec``` command allows you to execute a command inside a container just like ```docker exec``` does.

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
In the Pods section we saw that we could expose a port on a container but were unable to access it from outside the container network, to solve this problem we need to employ a Service. Services allow us to deal with pod failure or reallocation of a pod to a different node in the cluster. A service defines networking rules for accessing Pods in the cluster and from the internet. For example it is possible to declare a service to access a group of Pods using labels, the service receives a fixed IP address that can be use externally to reach it, Kubernetes then handles the internal routing (and load balacing) so that the request reaches a pod with the target lebel, e.g. webserver.

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

Once our deployments have been started by Kubernetes it's possible for us to manually scale the tiers we'd like to (namely the data and app tiers as the data tier cannot be scaled), to do this we can use the ```scale``` command, however in reality we don't want scale to be done manually, we want this to be controlled automatically, for which we need Autoscaling. 

## Autoscaling
Kubernetes supports CPU-based autoscaling and autoscaling based on a custom metric that you can define. We're gonna be focusing on CPU-scaling. Autoscaling works by specifying a desired target CPU percentage and a minimum and a maximum number of allowed replicas. The CPU percentage is expressed as a percentage of the CPU resource request of that Pod. Recall that Pods can set resource requests for CPU to ensure that they're scheduled on a node with at least that much CPU available. If no CPU request is set, autoscaling won't take any action. Kubernetes will increase or decrease the number of replicas according to the average CPU usage of all of the replicas. The autoscaler will also increase the number of replicas when the actual CPU usage of the current Pods exceeds the target and vice versa for decreasing the number of Pods. It will never create more replicas than the maximum nor will they decrease the number of replicas below your configuring minimum. You can configure some of the parameters of the autoscaler, but the default will work fine for us. With the defaults, the autoscaler will compare the actual CPU usage to the target CPU usage. And either increase the replicas if the actual CPU is sufficiently higher than the target, or it will decrease the replicas if the actual CPU is sufficiently below the target. Otherwise it will keep the status quo. Autoscaling depends on metrics being collected in the cluster.

Kubernetes integrates with several solutions for collecting metrics. We're going to be using the Metrics Server which is a solution that is maintained by Kubernetes itself. There are several manifest files on the Kubernetes Metrics Server GitHub repo that declare all of the resources. We will need to get Metrics Server up and running before we can use autoscaling. Once Metrics Server is running, autoscalers will retrieve those metrics and then make calls with the Kubernetes metrics API. The lab instance includes a Metrics Server manifest in the Metrics Server [sub-directory](https://github.com/cloudacademy/intro-to-k8s/tree/master/src/metrics-server). It's outside the scope of this course to discuss all the resources that comprise of the Metrics Server. So all we need to do is create them and we can count on metrics being collected in the cluster.

### Configuration
[6.2-autoscale.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/6.2-autoscale.yaml) shows how configure autoscaling for a Deployment, the ```app-tier``` deployment in this case.
* Autoscaling has it's own ```apiVersion```, ```autoscaling/v1```
* The ```kind``` is ```HorizontalPodAutoscaler```
* The ```spec``` contains the maximum and minimum number of replicas along with a reference to the target resource to scale and finally the ```targetCPUUtilizationPercentage``` field sets the target average CPU percentage across the replicas. With the target set to 70%, Kubernetes will decrease the number of replicas if the average CPU utilization is 63% or below and increase replicas if it is 77% or higher.

Note the contents of this file could have been achieved at the command line using ```kubectl autoscale deployment app-tier --max=5 --min=1 --cpu-percent=70```.

## Rolling Updates and Rollbacks
The last topic we will discuss on deployments is how updates work. Kubernetes uses rollouts to update deployments. And a Kubernetes rollout is a process of updating or replacing replicas with new replicas matching a new deployment template. Changes may be configurations such as environment variables or labels, or also code changes which result in the updating of an image key of the deployment template. In a nutshell, any change to the deployment's template will trigger a rollout.

Kubernetes has two different types of rollout strategy:
* Rolling is the default option, replicas are updated in groups, instead of all at once until the rollout is complete. This allows service to continue uninterrupted while the update is being rolled out. However, you need to consider that during the rollout there will be pods using both the old and new configuration of the application. In such, it should gracefully handle that.
* As an alternative deployments can also be configured to use the recreate strategy which kills all of the old template pods before creating the new ones. That, of a course, incurs downtime.

Note that auto-scaling and rollouts are compatible with each other.

The deployment strategy forms part of the ```spec``` in a section called ```strategy```:
* The ```type``` is ```RollingUpdate```
* ```matchSurge``` specifies how many replicas over the desired total are allowed during a rollout i.e. if this value was ```25%``` and the number of replicas was 8 then we could have up to 10 replicas live during a rollout, so higher surge allows new pods to be created without waiting for old ones to be deleted
* The ```maxUnavailable``` controls how many old pods can be to be deleted without waiting for new pods to be ready

You may want to configure these values if you want to trade off the impact on availability or resource utilization with the speed of the rollout. For example, you can have all of the new pods start immediately, but in the worst case you can have all of the new pods and all the old pods consuming resources at the same time effectively doubling the resource utilization for a short period.

```yaml
spec:
  strategy:
    rollingUpdate:
      maxSurge: 25%
      maxUnavailable: 25%
    type: RollingUpdate
```

## Probes
Kubernetes assumes that a Pod is ready as soon the container is started, but that's not always true. For example, if the container needs time to warm up Kubernetes should wait before sending any traffic to the new Pod. It's also possible that a Pod is fully operational but after some time it becomes un-responsive. For example, if it enters a deadlock state, Kubernetes shouldn't send any more requests to that Pod and will be better off to restart a new Pod.

Kubernetes supports a few different types of probe:
* The first type of probe is for **readiness** checks, these are used to probe when a Pod is ready to serve traffic.
* The second type of probe is called a liveness probe. They are used to detect when a Pod has entered a broken state and can no longer serve traffic. In this case, Kubernetes will restart the Pod for you.

That is the key difference between these two types of probes. Readiness probes determine when a service can send traffic to a Pod because it is temporarily not ready and a liveness probe decides when a Pod should be restarted because it won't come back to life. You declare both probes in the same way. You just have to decide which course of action is appropriate if a probe fails. Stop serving traffic or restart. These do seem very similar however consider what would happen if an external service that a pod depends on goes offline, if your pod can only recover when that service is back online then there is no point restarting it, provided it can recovery gracefully from the external service being unavailable.

Probes can be declared on containers in a Pod. All of the Pod's container probes must pass for the Pod to pass. You can define any of the following as the action probe to check the container:
* A simple command that runs inside of a container (the command probes succeeds if the exit code of the command is zero, else, it will fail)
* An HTTP GET request (a GET request succeeds if the response code is between 200 and 399)
* The opening of a TCP socket (a TCP socket probes succeeds if a connection can be established)

By default, the probes check the Pods every 10 seconds.

[7.2-data_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/7.2-data_tier.yaml) contains the probes below:
```yaml
        livenessProbe:
          tcpSocket:
            port: redis # named port
          initialDelaySeconds: 15
        readinessProbe:
          exec:
            command:
            - redis-cli
            - ping
          initialDelaySeconds: 5
```

Note that a command is specified as a list of strings, so in this case the command that will be executed in the container is ```redis-cli ping```.

By default three sequential probes need to fail before a probe is marked as failed, so that we have some buffer. Kubernetes won't immediately restart the Pod the first time the probe fails, but we can configure it that way if we need to.

[7.3-app_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/7.3-app_tier.yaml) contains more probe definitions:
```yaml
          - name: DEBUG
            value: express:*
        livenessProbe:
          httpGet:
            path: /probe/liveness
            port: server
          initialDelaySeconds: 5
        readinessProbe:
          httpGet:
            path: /probe/readiness
            port: server
          initialDelaySeconds: 3
```

## Init Containers
Sometimes you need to perform some tasks or check some prerequisites before a main application container starts. Some examples include waiting for a service to be created, downloading files, or dynamically deciding which port the application is going to use. The code that performs those tasks could be crammed into the main application, but it is better to keep a clean separation between the main application and supporting functionality to keep the smallest footprint you can for the images. However, the tasks are closely linked to the main application and are required to run before the main application starts. So Kubernetes provides us with an init container as a way to run these tasks that are required to complete before our main container starts. Pods may declare any number of init containers. They run in a sequence in the order they are declared. Each init container must run to completion before the following init container begins. And once all of the init containers have completed the main containers in the pods can start.

Init containers use different images from the containers in the pod, and this can provide some benefits. They can contain utilities that are not desirable to include in the actual application image for security reasons. They can also contain utilities or custom code for setup that is not present in the application image. For example, there is no need to include utilities like sed or awk or dig in an application image if they are only used for setup. Init containers also provide an easy way to block or delay the start-up of an application until some pre-conditions are met. They are similar to readiness probes in this sense but only run at pod startup.

**Init Containers run every time a pod is created.** This means they will run once for every replica in a deployment. And if a pod restarts, to say, due to failed live-ness probes the init containers would run again as part of that restart. Thus, you have to assume that init containers run at least once. This usually means that init containers should be unique. Running it more than once should have no additional effect.

[8.1-app_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/8.1-app_tier.yaml) contains an example init container:
```yaml
      initContainers:
        - name: await-redis
          image: lrakai/microservices:server-v1
          env:
          - name: REDIS_URL
            value: redis://$(DATA_TIER_SERVICE_HOST):$(DATA_TIER_SERVICE_PORT_REDIS)
          command:
            - npm
            - run-script
            - await-redis
```

## Volumes
Containers in a pod share the same network stack, but each has their own file system. It could be useful to share your data between containers. For example, having an init container prepare some files that the main container depends upon. The file system of containers are also limited to the lifetime of the container, so this could present some undesirable effects. For example, if the data tier container we are using in our examples crashes or fails a likeness probe, it will be restarted, and all of our data will be lost forever. So this lesson is going to cover the different ways Kubernetes handles non-ephemeral data that bring data from containers, Kubernetes volumes, and Kubernetes persistent volumes. Our goal for this lesson is to deploy the data tier from our sample application, using a persistent volume so the data can outlive the data tier pod. Again, this lesson builds on the code from the previous lessons, so let's first discuss more about the options for storing persistent data and then apply them to our data tier.

Kubernetes includes two different data storage types. Both are used by mounting a directory in one container, and then that could be **shared by containers in the same pod**. Pods can also use more than one volume or persistent volume. Their differences are mainly in how their lifetime is managed. One type exists for the lifetime of a particular pod and the other is independent from the lifetime of the pods. Volumes are tied to a pod and their life cycle. Volumes are used to share data between containers in a pod and to tolerate container restarts. Although you can configure volumes to use durable storage types that survive pod deletion, you should consider using volumes for non-durable storage that is deleted when the pod is deleted. Default type of volume is called emptyDir and it creates an initially empty directory on the node running the pod to back the storage used by the volume. Any data written to the directory remains if a container in the pod is restarted. Once the pod is deleted, the data in the volume is permanently deleted. It's worth noting that since the data is stored on a specific node, if a pod is rescheduled to a different node, that data will be lost. **If the data is too valuable to lose when a pod is deleted or rescheduled, you should consider using persistent volumes.** Persistent volumes are independent from the lifetime of pods and are separately managed by Kubernetes. They work a little bit differently.

Pods may claim a persistent volume and use it throughout their lifetime. The persistent volumes will continue to exist outside of their pods. Persistent volumes can even be mounted by multiple pods on different nodes if the underlying storage supports multiple readers or writers. Persistent volumes can be provisioned statically in advance by a cluster admin or dynamically for a far more flexible self-serve use case. Pods must make a request for storage before they can use a persistent volume. The request is made using a persistent volume claim, or PVC. A PVC declares how much storage the pod needs, the type of persistent volume, and the access mode. The access mode describes the persistent volume and whether it is mounted in read-only, read-write, or read-write many. **There are three supported access modes to choose from, read-write once, read-only many, or read-write many.** If there isn't a persistent volume available to satisfy the claim and dynamic provisioning isn't enabled, the claim will stay in a pending state until such persistent volume is ready. The persistent volume claim is connected to the pod by using a regular volume with the type set to persistent volume claim.

Both volumes and persistent volumes may be backed by a wide variety of volume types. It is usually preferable to use persistent volumes for more durable types and volumes for more ephemeral storage needs. Durable volume types include the persistent disks in many cloud vendors, such as Google Cloud Engine persistent Disks, Azure Disks, and Amazon Elastic Block Store. There's also support for more generic volume types, such as network file system or NFS and iSCSI.

For the purpose of this example a new namespace will be used, it is declared in [9.1-namespace.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/9.1-namespace.yaml). There are three additions to the manifest (vs. [7.2-data_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/7.2-data_tier.yaml)), a persistent volume, a persistent volume claim, and a volume to connect the claim to the pod.

```yaml
---
apiVersion: v1
kind: PersistentVolume
metadata:
  name: data-tier-volume
spec:
  capacity:
    storage: 1Gi # 1 gibibyte
  accessModes:
    - ReadWriteOnce
  awsElasticBlockStore: 
    volumeID: INSERT_VOLUME_ID # replace with actual ID
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: data-tier-volume-claim
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 128Mi # 128 mebibytes 
---
...
        volumeMounts:
          - mountPath: /data
            name: data-tier-volume
      volumes:
      - name: data-tier-volume
        persistentVolumeClaim:
          claimName: data-tier-volume-claim
```

* First is our persistent volume. This is the raw storage where the data is ultimately written to by the pod's container. It has a declared storage capacity and other attributes. Here we've allocated one gibibyte. The access mode of read-write once means this volume may be mounted for reading and writing by a single node at a time. **Note that it is a limit on the node attachment, not pod attachment.** Persistent volumes may list multiple access modes in the claim that specifies the mode it requires. The persistent volume can only be claimed in a single access mode at any time. Lastly, we have an AWS Elastic Block Store mapping, which is specific to the type of storage backed by the PV. You would use a different mapping if you were not using an EBS volume for storage. And the only required key for AWS's Elastic Block Store is the volume ID, which is uniquely identified by the EBS volume. It will be different in your environment than mine, so I've added an insert volume ID placeholder that we will place before we recreate the PV. 
* Next, we have the persistent volume claim. The PVC spec outlines what it is looking for in a persistent volume. For a persistent volume to be bound to a PVC, it must satisfy all the constraints in the claim. We are looking for a persistent volume that provides the read-write once access mode and has at least 128 mebibytes of storage. The claim request is less than or equal to the persistent volume's capacity and the access mode overlaps with the available access modes in the persistent volume. This means that the PVC request is satisfied by our persistent volume and will be bound to it.
* Lastly, the appointment's template now includes a volume which links the PVC to deployments pod. This is accomplished by using the persistent volume claim mapping and setting the claim name to the name of the persistent volume, which is data tier volume claim. You will always use persistent volume claim when working with PVs. If you wanted to use an ephemeral storage volume, you would replace it with an emptyDir mapping or other types that don't connect to a persistent volume.

Volumes can be used in the pods containers and init containers, but they must be mounted to be available in the containers. The volume mounts list includes all the volume mounts for given container. The mount pass for different containers could be different even if the volume is the same. In our case, we only have one, and we are mounting the volume at slash data, which is where the Redis is configured to store its data. This will cause all of the data to be written to the persistent volume.

To round off the example (in the new namespace) we will need to create the [9.3-app_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/9.3-app_tier.yaml) and [9.4-support_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/9.4-support_tier.yaml).

## ConfigMaps and Secrets
Up until now, the deployment template has included all of the configuration required by Pod containers. This is a big improvement over storing the configuration inside the binary or container image. Having configuration in the Pod spec, also makes it a lot less portable. Furthermore, if the configuration involves sensitive information, such as passwords, API keys, this also presents a security issue.

So Kubernetes provides us with ConfigMaps and Secrets, which are Kubernetes resources that you can use to separate the configuration from the Pod specs. This operation makes it easier to manage and change configurations. It also makes for more portable manifests. ConfigMaps and Secrets are very similar and used in the same way when it comes to Pods. One key difference is that Secrets are specifically for storing sensitive information. Secrets reduce the risk of their data being exposed. However, the cluster admin also needs to ensure that all the proper encryption and access control safeguards are in place to actually consider Secrets being safe. We'll focus on Secrets and leave out the security details from this introductory course. Another difference is that Secrets have specialized types for storing credentials, such as requiring to pull images from registries. They also are good at storing TLS private keys and certificates. But I'll refer you to the official documentation we need to make use of those capabilities. ConfigMaps and Secret store data as key value pairs. Pods must reference ConfigMaps or Secrets to use their data. Pods can use the data by mounting them as files through a volume or as environment variables. We'll see examples of these in the demo.

### ConfigMap
We're going to be using a ConfigMap to configure Redis using a volume to mount a Config file will use and a Secret to inject sensitive environment variables into the app tier. First, let's create a Config namespace for this demo. With [10.1.namespace.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/10.1-namespace.yaml). Next consider the config file itself [10.2-data_tier_config.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/10.2-data_tier_config.yaml):

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: redis-config
data:
  config: | # YAML for multi-line string
    # Redis config file
    tcp-keepalive 240
    maxmemory 1mb
```

First notice that there is no spec rather than we have key value payers of the ConfigMap stores and they're under a mapping named data. Here we have a single key named Config. You can have more than one but one is enough for our purpose. The value of Config is a multiline string that represents the file contents of a Redis configuration file. The bar or pipe symbol after ConfigMap is Yaml for starting a multiline string and causes all the following lines to be the value of Config including the Redis Config comment. The configuration files set the TCP keep alive in max memory of Redis. These are arbitrarily chosen for this example. Separating the configuration makes it easy to manage configuration separately from the Pod spec. we will have to make some initial changes to the Pod to make use of the ConfigMap but after that, the two can be managed separately.

To add this ConfigMap to the data tier itself we need to map it as a volume, see [10.3-data_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/10.3-data_tier.yaml).

```yaml
        command:
          - redis-server
          - /etc/redis/redis.conf
        volumeMounts:
          - mountPath: /etc/redis
            name: config
      volumes:
        - name: config
          configMap:
            name: redis-config
            items:
            - key: config
              path: redis.conf
```

A new ```configMap``` type of volume is added and it references the ```redis-config``` ConfigMap we just saw. Items declare which key value pair we want to use from the ConfigMaps. We only have one in our case, and that is ```config```.

If you have multiple environments, you could easily do things like referencing a dev configuration in one environment and a production configuration in another. The ```mountPath``` sets the path of the file that will be mounted with a ```config``` value. This is relative to the mount point of the volume. Up above in the container spec, the volume mounts mapping declares the use of the Config volume and mounts it at ```/etc/redis```. So the full absolute path of the ```config``` path, will be ```/etc/redis/redis.conf```. The last change that we need is to use a custom command for the container so that Redis knows to load the Config file when it starts. We do that by setting the ```redis-server```, ```/etc/redis/redis.conf``` as the command. With this setup, we can now independently configure Redis without touching the deployment template. As a quick side note, before we create the resources, if we're dealing with a Secret rather than a ConfigMap, the volume type would be ```secret``` rather than ```configMap```  and the ```name``` key would be replaced with ```secret``` name. Everything else would be the same.

### Secret
Now we can quickly see how secrets work and see the similarities they have with a ConfigMap. We will add a secret to the app-tier using [10.4-app_tier_secret.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/10.4-app_tier_secret.yaml) and an environment variable. It won't have any functional impact but it will show the idea. Here is our Secret manifest, I mentioned upfront that you usually don't wanna check in secrets to source control given their sensitive nature. It makes more sense to have secrets managed separately. You could still use manifest files as we are here, or the Secret could be created directly with kube control:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-tier-secret
stringData: # unencoded data
  api-key: LRcAmM1904ywzK3esX
  decoded: hello
data: #for base-64 encoded data
  encoded: aGVsbG8= # hello in base-64

# api-key secret (only) is equivalent to
# kubectl create secret generic app-tier-secret --from-literal=api-key=LRcAmM1904ywzK3esX
```

The command at the bottom of the file shows how to create the same Secret without a manifest file.

Focusing on the manifest file, we can see that itself has a similar structure as our ConfigMap, except for the ```kind``` being ```Secret``` rather than ```ConfigMap```. And Secrets can use a string data mapping in addition to the data one we use in our ConfigMap. **As part of the effort to reduce the risk of Secrets being exposed in plain text, they are stored as base-64 encoded strings and Kubernetes automatically decodes them when using a container.** I also have to point out that ```base-64``` encoding does not really offer any additional security. It's not encrypting the values, and anyone can decode ```base-64```, so to continue to treat the encoded strings as sensitive data. With that cautionary statement out of the way, the ```stringData``` mapping allows you to specify Secrets without first encoding them because kubernetes will coding them for you. It's simply a convenience. **If you use the ```data``` mapping, you must specify in encoded values.** The API key secret is the one that we will use in the app tier, but I've included the encoded and decoded key value pairs to illustrate the ```base-64``` encoding.

Looking at the manifest which supports this secret [10.5-app_tier.yaml](https://github.com/cloudacademy/intro-to-k8s/blob/master/src/10.5-app_tier.yaml) we can see the following has been added:

```yaml
          - name: API_KEY
            valueFrom:
              secretKeyRef:
                name: app-tier-secret
                key: api-key
```

The ```API_KEY``` environment variable is added. The ```valueFrom``` mapping is used to reference it from the source for the value. Here, the source is Secret, so the ```secretKeyRef``` is used. If you need to get the environment variable from a ConfigMap rather than a Secret, you would use the ```configMapKeyRef``` instead of ```secretKeyRef```. The name is the name of the Secret, and the key is the name of the key in the Secret you want to get the value from.

Just like with using volumes to reference Secrets or Config maps, you should restart a roll out to how the deployment pods restart with a new version of the environment variables. Environment variables do not update on the flight like volumes. So actively managing the rollout is must.

## Conececting to EC2 from WSL2
Begin by donwloading the PEM file and copying it into the Linux filesystem, this can be done by navigating to the directory you want to file to be (in Linux) and typing ```explorer.exe .``` which will open a normal file explorer in that dir. Copy the file in via the GUI and then back at the command line ensure it has the correct permissions: ```chmod 600 file.pem```. Finally connect to the Bastion Public IP: ```ssh -i file.pem username@ip-address```.

**Links**
* https://www.clickittech.com/aws/connect-ec2-instance-using-ssh/#3
* https://www.howtogeek.com/fyi/windows-10-will-finally-offer-easy-access-to-linux-files/

## The Kubernetes Ecosystem

### Helm
Helm is Kubernetes' package manager. You write packages called charts. Then you use the helm CLI to install and upgrade charts as releases on your cluster. Charts contain all the resources like services and deployments required to run a particular application. Helm charts make it easy to share and complete applications built for Kubernetes.

Helm charts can be found on the Helm hub at [hub.helm.sh](https://artifacthub.io/)

### Kustomize
Kustomize allows you to customize YAML manifests in Kubernetes. It can help you manage the complexity of your applications running inside of Kubernetes. An obvious example is managing different environments such as tests and stage. We saw how we could use config maps and secrets to help with that. But Kustomize makes it even easier. Kustomize works by using customization dot YAML file that declares rules for customizing or transforming resource manifest files. The original manifests are untouched and remained usable as they are, which is an, a massive benefit compared to other tools that required templating in the manifest, rendering them unusable on their own.

Examples
* Generating ConfigMaps and Secrects from files
* Configure common fields across multiple resources
* Apply patches to any field in a manifest
* Use overlays to customize base groups of resources

Kustomize has been directly integrated with kubectl since Kubernetes 1.14. The kubectl customized command prints the customized resource manifests with customization defined in customization dot YAML. To accept the customization and then realize them in your cluster you can include the --kustomize or - k option to cube control create or apply.

### Prometheus
Prometheus is an open source monitoring and alerting system. Prometheus's built on top of many components, but at its core is a server for pulling in time series metric data and storing it. Prometheus was originally inspired by an internal monitoring tool at Google called borgmon. Similar to how Kubernetes itself was inspired by the board project at Google. Given that history it should come as no surprise that Prometheus is the de facto standard solution for monitoring Kubernetes.

Prometheus and Kubernetes play very nicely together
* Kubernetes components supply all their own metris in Prometheus format
  * Many more metrics than the build in Metrics Server
* Adapter available to autoscale using metrics in Prometheus rather than CPU utilisation
* Commonly paired with Grafana for visulatsations and dashboards
* Define alert rules and send notifications
* Easily installed via a Helm chart

### Kubeflow
Kubeflow is aimed at making deployment of machine learning workloads on Kubernetes simple, scalable, and portable. Kubeflow is a complete machine learning stack. You can use it for complete end to end machine learning including building models, training them, and serving them all within Kubeflow. Being built on Kubernetes, you can deploy it anywhere and get all of the nice features that Kubernetes provides like auto-scaling. Definitely check out Kubeflow if your requirements involve machine learning. 

### Knative
Knative is a platform built on top of Kubernetes for building, deploying, and managing serverless workloads. Serverless has gained a lot more momentum because it allows developers and companies to focus more on the code and less on the servers that run it. This trend started with AWS Lambda which is synonymous with serverless. However, as the industry shifts to multi-cloud and avoiding vendor lock-in solutions built on top of Kubernetes can be deployed anywhere. This gives you the portability that you would get with containers, but for your entire serverless platform. Knative is not the only game in town when it comes to serverless but it does have the support of industry heavyweights like Google, IBM, and SAP. 

## Multi-container Patterns
Kubernetes pods allow you to have multiple containers sharing the same network space and can also share storage between containers, often using a single container is the right choice for a pod, but there are several common patterns for when you should use multiple containers. **Pods are an extra level of abstraction above containers.** What benefits do we get by having this extra level of abstraction? Containers alone aren't enough for Kubernetes to effectively manage workloads. **Pods allow you to specify additional information such as restart policies and probes to check the health of containers.** Pods also allow you to seamlessly deal with different types of underlying containers, for example, Docker and Rocket. You deal with pods regardless of the underlying container runtime by allowing multiple containers to share network and storage in a pod, you can have tightly coupled containers co-located and managed as a single unit, without needing to package them as a single container image. This allows for better separation of concerns, and can improve container image reusability.

### The Sidecar Pattern
This is the most common multi-container pattern. As the name suggests, the sidecar pattern uses a helper container to assist a primary container. Common examples include logging agents that collect logs and ship them to a central aggregation system. Other examples include file sync services and watchers. All of these examples add useful functionality to the main container, and can be accomplished by adding a sidecar, rather than burdening the main container with additional responsibilities. It makes it easier for different development teams to work on each application separately, and also makes testing easier. Furthermore, you get the benefit of failure isolation. If the sidecar fails, say the logging agent fails, then the main container, say a web server, can continue to surf traffic. You can also independently update the sidecar container. It's worth pointing out here that all of these benefits are also true for the other multi-container design patterns that we'll cover.

**Example - A file sync sidecar**
The primary container in the pod is the web server container. The sidecar is a content puller. The content puller syncs with an external content management system, or CMS, to get the latest files for the web server to serve. The web server serves the content to any clients that request it. How does the web server get the latest content from the content puller? They share the content by using a shared storage volume. The sidecar pattern is covered in depth in the Kubernetes observability lab. You'll see a pod manifest for configuring the sidecar pattern there.

### The Ambassador Pattern
The ambassador pattern uses a container to proxy communication to and from a primary container. The primary container only needs to consider connecting to localhost, while the ambassador controls proxying the connections to different environments. This is because containers in the same pod share the same network space, and can communicate with each other over localhost. This pattern is commonly used to communicate with a database. You can configure environment and variables in the primary container to control the database connection, but with the ambassador pattern, the application can be simplified to always connect to localhost, with the responsibility of connecting to the right database given to the ambassador. In production environments, the ambassador can implement logic to work with sharded databases as well, but the application in the primary container only needs to consider a single logical database, accessible over localhost. Some of the other benefits of the ambassador pattern are that during application development you can run a database on your local machine without requiring the ambassador, keeping the development experience simple. The ambassador may also be used by multiple applications written in different languages, since that responsibility is taken out of the primary application.

**Example - A web app that uses a database for persistence**
The primary container is the web app and the ambassador is a database proxy container. The web app handles requests from clients and when the data needs to be updated the web app sends a request over local host, where it is received by the database proxy. The database proxy then forwards the request to the correct database backend. The database could have a single endpoint, or the database could be shared across multiple database instances. The ambassador can encapsulate the logic for sharding the requests in the latter case. Meanwhile, the web app is free from any of the associated complexity.

### The Adaptor Pattern
The adaptor pattern uses a container to present a standardized interface across multiple pods. For example, presenting an interface for accessing output in a standardized format for logs, across several applications. The adaptor pattern is the opposite of the ambassador pattern, in that the ambassador presents a simplified view to the primary container while the adaptor pattern presents a simplified view of the application to the outside world. The adaptor pattern is commonly used for normalizing application logs, or monitoring data, so they can easily be consumed by a shared aggregation system. The adaptor may communicate with the primary container using either a shared volume when dealing with files or over localhost.

**Example - Getting metric data from a rest API**
The adaptor pattern allows you to adapt an application output without requiring code changes. This may be required when you do not have access to an applications source code. Even if you do have access to the source, it is a cleaner separation of concerns to use an adaptor for each potential interface that may be required, rather than burdening the application with that complexity.

## Networking
The basic building block in Kubernetes is a pod. The network model for a pod is **IP per pod** meaning each pod is assigned one unique IP in the cluster. Containers in the pod share the same IP address and can communicate with each other's ports using localhost. A pod is of course scheduled onto a node in the cluster. Any node can reach the pod by using its pod IP address. Other pods in the cluster can also reach the pod using the pod's IP address. This is thanks to whatever Kubernetes networking plugin you choose. The network plugin implements the container network interface standard and enables pod-to-pod communication. But **pods should be seen as ephemeral**. They can be killed and restarted with a different IP. You may also have multiple replicas of a pod running. You can't rely on a single pod IP to get the benefits of replication. This is where services come in. 

### Services
The service maintains a logical set of pod replicas. Usually these sets of pods are identified with labels. The service maintains a list of endpoints as pods are added and removed from the set. The service can send requests to any of the pods in the set. Clients of the service now only need to know about the service rather than specific pods. Pods can discover services using environment variables **as long as the pod was created after the service**, or by using the DNS add-on in a cluster. The DNS can resolve the service name or the namespace qualified service name to the IP associated with the service. The IP given to a service is called the cluster IP. Cluster IP is the most basic type of service. The cluster IP is only reachable from within the cluster. The kube-proxy cluster component that runs on each node is responsible for proxying requests for the service to one of the service's endpoints. The other types of services allow clients outside of the cluster to connect to the service.

The first of those types of services is node port. Node port causes a given port to be opened on every node in the cluster. The cluster IP is still given to the service. Any requests to the node port of any node are routed to the cluster IP.

The next type of service that allows external access is load balancer. The load balancer type exposes the service externally through a cloud provider's load balancer. A load balancer type also creates a cluster IP and a node port for the service. Requests to the load balancer are sent to the node port and routed to the cluster IP. Different features of cloud provider load balancers, such as connection draining and health checks, are configured using annotations on a load balancer.

The final type of service is the external name and it is different in that it is enabled by DNS, not proxying. You configure an external name service with the DNS name and requests for the service return a CNAME record with the external DNS name. This can be used for services running outside of Kubernetes, such as a database as a service offering.

### Policies
Network policies in Kubernetes are rules that determine which group of pods are allowed to communicate with each other and to other network endpoints. Network policies are similar to simple firewalls or security groups that control access to virtual machines running in a cloud. Network policies are namespaced resources meaning that you can configure network policies independently for each Kubernetes namespace. Before we get into the details there is an important caveat when it comes to network policies. **The container network plugin running in your cluster must support network policies to get any of their benefits.** Otherwise, you will create network policies and there won't be anything to enforce them. In the worse case, you might think that you have secured access to an application but the pods are actually still open to request from anywhere. There won't be any error messages when you create the policy. It will be created successfully but we'll simply have no effect. The cluster administrator can tell you if the network plugin in your cluster supports network policy or not. Some examples of network plugins that support network policy are Calico and Romana.

With that caveat out of the way, we can talk about two kinds of pods, isolated and non-isolated. A pod that is non-isolated allows traffic from any source. This is the default behaviour. Once a pod is selected by a network policy it becomes isolated. The pods are selected using labels, which are the core grouping primitive in Kubernetes. Let's see how to use network policies in a demo by writing several network policies and observing their effects. To begin with, I am running a cluster with the Calico network plugin installed. You can see that by checking the pods in the kube-system namespace, and observe there're several pods beginning with calico.

For network policies you should think of them as sets of allow rules that are added together. If any rule allows traffic, even if others deny it, the traffic is allowed. The other important thing to note is that the rules apply to creating connections, not traffic sent over established connections.

## Service Accounts

## Leaveragine kubectl
