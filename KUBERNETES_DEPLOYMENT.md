# Kubernetes Deployment Guide for CMS API (Without Docker)

## Prerequisites

- Kubernetes cluster (minikube, Docker Desktop K8s, or cloud provider)
- kubectl installed
- Jenkins configured with kubectl access

## Setup Steps

### 1. Prepare Container Image

You have 3 options:

**Option A: Build image locally and load to cluster**
```bash
docker build -t cms-api:latest .
# For minikube:
minikube image load cms-api:latest
# Or push to registry and update image reference
```

**Option B: Use pre-built image from registry**
- Update `k8s-deployment.yaml` image field with your registry

**Option C: Use existing image**
- Keep `your-registry/cms-api:latest` and ensure image exists in cluster

### 2. Update Configuration

Edit **k8s-deployment.yaml**:
```yaml
image: your-registry/cms-api:latest  →  Your image reference
db-url                                →  Update database URL
db-user/db-password                   →  Update database credentials
```

### 3. Deploy to Kubernetes

```bash
# Create namespace (optional)
kubectl create namespace cms

# Apply deployment
kubectl apply -f k8s-deployment.yaml -n default

# Check deployment status
kubectl get deployments -n default
kubectl get pods -n default
kubectl describe pod <pod-name> -n default

# View logs
kubectl logs -f deployment/cms-api -n default

# Access service
kubectl get service cms-api-service -n default
```

### 4. Verify Deployment

```bash
# Check running pods
kubectl get pods -n default -l app=cms-api

# Test health endpoint
kubectl port-forward svc/cms-api-service 8080:80 -n default
curl http://localhost:8080/actuator/health
```

## Jenkins Pipeline

The pipeline now:
1. **Checkout** - Get source code
2. **Build** - Compile Spring Boot app
3. **Test** - Run unit tests
4. **Package** - Create JAR file
5. **Deploy to Kubernetes** - Apply k8s manifests

### Configure Jenkins for kubectl

```bash
# Add kubeconfig to Jenkins:
# Jenkins > Manage Jenkins > Manage Plugins > Install "Kubernetes CLI"
# Jenkins > Manage Jenkins > Configure System > add Kubernetes config
```

## Kubernetes Components Created

- **Deployment**: cms-api (3 replicas)
- **Service**: cms-api-service (LoadBalancer)
- **ConfigMap**: cms-config (database configuration)
- **Secret**: cms-secrets (database credentials)

## Troubleshooting

### ImagePullBackOff error
```bash
# Verify image exists in cluster
kubectl get images -n default  # minikube
# Or push image to accessible registry
```

### Pod won't start
```bash
kubectl describe pod <pod-name> -n default
kubectl logs <pod-name> -n default
```

### Database connection failed
- Check ConfigMap and Secret values
- Verify database is accessible from cluster
- Check application logs

## Scaling

```bash
# Scale deployment
kubectl scale deployment cms-api --replicas=5 -n default

# Autoscale (requires metrics-server)
kubectl autoscale deployment cms-api --min=2 --max=10 --cpu-percent=80 -n default
```

## View Service Details

```bash
kubectl get svc cms-api-service -n default
kubectl describe svc cms-api-service -n default
```
