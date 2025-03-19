resource "kubernetes_service" "backend" {
  metadata {
    name      = "backend-service-${kubernetes_namespace.environment.metadata.0.name}"
    namespace = kubernetes_namespace.environment.metadata.0.name
  }
  spec {
    selector = {
      app = kubernetes_deployment.ranking-deployment.metadata.0.labels.app
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

resource "kubernetes_deployment" "ranking-deployment" {
  metadata {
    name = "backend-${kubernetes_namespace.environment.metadata.0.name}"
    labels = {
      app = "backend-${kubernetes_namespace.environment.metadata.0.name}"
    }
    
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "backend-${kubernetes_namespace.environment.metadata.0.name}"
      }
    }

    template {
      metadata {
        labels = {
          app = "backend-${kubernetes_namespace.environment.metadata.0.name}"
        }
      }

      spec {
        container {
          image             = "${var.REGISTRY_BACKEND}:${var.BACKEND_VERSION}"
          name              = "backend"
          image_pull_policy = "Always"
        }
      }
    }
  }

  depends_on = [
    kubernetes_namespace.environment
  ]
}
