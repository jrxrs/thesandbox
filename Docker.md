# Dockers

## Commands

```bash
docker run -it <image_name> /bin/bash
```
This runs a container and attaches to stdin, /bin/bash is the processed which is opened and ```-it``` is the interactive flag.

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
The start command will restart your container but it will not attach to it automatically like ```run -it``` did, so use ```attach``` instead.

```bash
docker stop <container_identifier>
```
Stop a running container.

```bash
docker rm <container_identifier>
```
Clean up any stopped containers by removing them.
