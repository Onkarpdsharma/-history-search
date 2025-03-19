
resource "helm_release" "nginx-ingress" {
  name       = "nginx-ingress-${kubernetes_namespace.environment.metadata.0.name}"
  repository = "https://kubernetes.github.io/ingress-nginx"
  chart      = "ingress-nginx"
  version    = "4.1.2"
  namespace  = kubernetes_namespace.environment.metadata.0.name
  wait          = false
  force_update  = true
  recreate_pods = true

  set {
    name  = "controller.service.loadBalancerIP"
    value = var.STATIC_IP
  }

  set {
    name  = "controller.publishService.enabled"
    value = true
  }

  set {
     name = "controller.ingressClassResource.name"
     value = "nginx"
 }


 
}


