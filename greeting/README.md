## Compose sample application
### Java application with Spring framework and a Postgres database

Project structure:
```
.
├── backend
│   ├── Dockerfile
│   └── ...
├── db
│   └── password.txt
├── compose.yaml
└── README.md

```

[_compose.yaml_](compose.yaml)
```
services:
  backend:
    build: backend
    ports:
    - 8080:8080
  db:
    image: postgres
    ...
```
The compose file defines an application with two services `backend` and `db`.
When deploying the application, docker compose maps port 8080 of the backend service container to port 8080 of the host as specified in the file.
Make sure port 8080 on the host is not already being in use.

## Deploy with docker compose

```
$ docker compose up -d
Creating network "spring-postgres_default" with the default driver
Building backend
Step 1/11 : FROM maven:3.5-jdk-9 AS build
3.5-jdk-9: Pulling from library/maven
...
Successfully tagged spring-postgres_backend:latest
WARNING: Image for service backend was built because it did not already exist. To rebuild this image you must use `docker-compose build` or `docker-compose up --build`.
Creating spring-postgres_backend_1 ... done
Creating spring-postgres_db_1      ... done
```

## Expected result

Listing containers must show two containers running and the port mapping as below:
```
$ docker ps
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                  NAMES
56236f640eaa        postgres                  "docker-entrypoint.s…"   29 seconds ago      Up 28 seconds       5432/tcp               spring-postgres_db_1
6e69472dc2c0        spring-postgres_backend   "java -Djava.securit…"   29 seconds ago      Up 28 seconds       0.0.0.0:8080->8080/tcp   spring-postgres_backend_1
```

After the application starts, navigate to `http://localhost:8080` in your web browse or run:
```
$ curl localhost:8080
<!DOCTYPE HTML>
<html>
<head>
  <title>Getting Started: Serving Web Content</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<p>Hello from Docker!</p>
</body>
```

Stop and remove the containers
```
$ docker compose down
Stopping spring-postgres_db_1      ... done
Stopping spring-postgres_backend_1 ... done
Removing spring-postgres_db_1      ... done
Removing spring-postgres_backend_1 ... done
Removing network spring-postgres_default
```

# git sparse
Git does not natively support cloning a single directory from a repository. However, you can achieve this using a feature called sparse checkout. Sparse checkout allows you to check out only a part of the working directory, which is particularly useful for large repositories where you only need a specific subdirectory.

Here are the steps to clone a specific directory from a Git repository using sparse checkout:

Initialize a New Git Repository:
Initialize a New Git Repository:

```
git init <repo>
cd <repo>
```

Add the Remote Repository:

```
git remote add -f origin <url>
```
Enable Sparse Checkout:

```
git config core.sparseCheckout true
```
Specify the Directory to Check Out:

```
echo "path/to/your/directory/" >> .git/info/sparse-checkout
```
Check Out the Specified Directory:

```
git checkout main
```
### Example
To clone the spring-postgres directory from the https://github.com/docker/awesome-compose.git repository, follow these steps:

Initialize a New Git Repository:

```
git init awesome-compose
cd awesome-compose
```
Add the Remote Repository:

```
git remote add -f origin https://github.com/docker/awesome-compose.git
```
Enable Sparse Checkout:

```
git config core.sparseCheckout true
```
Specify the Directory to Check Out:

```
echo "spring-postgres/" >> .git/info/sparse-checkout
```
Check Out the Specified Directory:

```
git checkout main
```
This will clone only the spring-postgres directory from the repository.

