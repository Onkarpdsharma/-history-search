
resource "kubernetes_ingress_v1" "nova_ingress" {
  metadata {
    name      = "${kubernetes_namespace.environment.metadata.0.name}-nextinit-ingress"
    namespace = kubernetes_namespace.environment.metadata.0.name
    annotations = {

      "kubernetes.io/ingress.class"                 = "nginx"
      "cert-manager.io/issuer"                      = "letsencrypt-${var.ENVIRONMENT}"
      "kubernetes.io/ingress.global-static-ip-name" = var.STATIC_IP
      
    }
  }

  spec {
     
    tls {
      hosts       = [var.DNS_COMPLETE]
      secret_name = "letsencrypt-${var.ENVIRONMENT}"
    }

    rule {
      host = var.DNS_COMPLETE
      http {

        path {
          backend {
            service {
              name = kubernetes_service.frontend.metadata.0.name
              port {
                number = 80
              }
            }
          }

          path = "/"
        }
       
      }
    }
  }

  depends_on = [
    kubernetes_namespace.environment
  ]
}
