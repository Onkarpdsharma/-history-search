
provider "kubectl" {
  host                   = var.KUBERNETES_HOST
  cluster_ca_certificate = base64decode(var.KUBERNETES_CLIENT_CA_CERTIFICATES)
  token                  = var.KUBERNETES_TOKEN
  load_config_file       = false
}
