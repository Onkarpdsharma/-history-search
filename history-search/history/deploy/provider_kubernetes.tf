provider "kubernetes" {

  host                   = var.KUBERNETES_HOST
  token                  = var.KUBERNETES_TOKEN
  cluster_ca_certificate = base64decode(var.KUBERNETES_CLIENT_CA_CERTIFICATES)
}
