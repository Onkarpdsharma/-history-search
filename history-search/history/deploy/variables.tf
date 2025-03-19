## PRIVATE REGISTRY
variable "REGISTRY_EMAIL" {
  default = ""
}

variable "FRONTEND_VERSION" {}
variable "BACKEND_VERSION" {}
variable "REGISTRY_FRONEND" {}
variable "REGISTRY_BACKEND" {}

variable "ENVIRONMENT" {}
variable "DNS_BACKEND" {}
variable "REGION" {}

## COMPLETE DNS
variable "DNS_COMPLETE" {}
variable "STATIC_IP" {}
variable "EMAIL" {}

## KUBERNETES VARIABLES
variable "KUBERNETES_HOST" {}
variable "KUBERNETES_TOKEN" {}
variable "KUBERNETES_CLIENT_CA_CERTIFICATES" {}
variable "KUBERNETES_CLUSTER_NAME" {}

## GOOGLE VARIABLES
variable "GKE_PROJECT_ID" {}
