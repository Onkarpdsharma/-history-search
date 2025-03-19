
resource "kubectl_manifest" "issuers" {
    yaml_body = <<YAML
apiVersion: cert-manager.io/v1
kind: Issuer
metadata:
  name: letsencrypt-${kubernetes_namespace.environment.metadata.0.name}
  namespace: ${kubernetes_namespace.environment.metadata.0.name}
spec:
  acme:
    #server:  https://acme-staging-v02.api.letsencrypt.org/directory
    server: https://acme-v02.api.letsencrypt.org/directory
    email: ${var.EMAIL}
    privateKeySecretRef:
      name: letsencrypt-${kubernetes_namespace.environment.metadata.0.name}-key
    solvers:
      - http01:
          ingress:
            class: nginx
YAML
}
