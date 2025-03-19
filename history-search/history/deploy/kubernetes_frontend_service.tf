resource "kubernetes_service" "frontend" {
  metadata {
    name      = "frotend-service-${kubernetes_namespace.environment.metadata.0.name}"
    namespace = kubernetes_namespace.environment.metadata.0.name
  }
  spec {
    selector = {
      app = kubernetes_deployment.frontend-deployment.metadata.0.labels.app
    }
    session_affinity = "None"
    port {
      port = 80
    }
     

    type = "LoadBalancer"
  }
  depends_on = [
    kubernetes_namespace.environment
  ]
}

resource "kubernetes_deployment" "fronend-deployment" {
  metadata {
    name = "frontend-${kubernetes_namespace.environment.metadata.0.name}"
    labels = {
      app = "frontend-${kubernetes_namespace.environment.metadata.0.name}"
    }
    
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "frontend-${kubernetes_namespace.environment.metadata.0.name}"
      }
    }

    template {
      metadata {
        labels = {
          app = "frontend-${kubernetes_namespace.environment.metadata.0.name}"
        }
      }

      spec {
        container {
          image             = "${var.REGISTRY_FRONTEND}:${var.FRONTEND_VERSION}"
          name              = "frontend"
          image_pull_policy = "Always"
        }
      }
    }
  }

  depends_on = [
    kubernetes_namespace.environment
  ]
}
