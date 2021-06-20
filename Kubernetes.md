# Kubernetes

Kubernetes, often abbreviate as K8s, is an open-source container-orchestration tool designed to automate, deploying, scaling, and the operation of containerized applications.

Kubernetes was born out of Google's experience running workloads in production on their internal Borg cluster manager for well over a decade, it is designed to grow from tens, thousands, or even millions of containers. Organizations adopting Kubernetes increased their velocity by having the ability to release faster and recover faster with Kubernetes self healing mechanisms. Kubernetes is a distributed system. Multiple machines are configured to form a cluster. Machines may be a mix of physical and virtual and they may exist on-prem or in cloud infrastructure each with their own unique hardware configurations.

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

