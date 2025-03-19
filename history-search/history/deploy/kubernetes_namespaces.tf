resource "kubernetes_namespace" "environment" {
  metadata {
    name = var.ENVIRONMENT
    labels = {
      "cert-manager.io/disable-validation" = true
    }
  }
}
