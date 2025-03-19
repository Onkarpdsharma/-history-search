resource "kubernetes_service_account_v1" "tiller" {
  metadata {
    name      = "tiller-service-account-${kubernetes_namespace.environment.metadata.0.name}"
    namespace = kubernetes_namespace.environment.metadata.0.name
  }
  automount_service_account_token = true
}

resource "kubernetes_cluster_role_binding_v1" "tiller" {
  metadata {
    name = "tiller-role-binding-${kubernetes_namespace.environment.metadata.0.name}"
  }

  role_ref {
    api_group = "rbac.authorization.k8s.io"
    kind      = "ClusterRole"
    name      = "cluster-admin"
  }

  subject {
    api_group = ""
    kind      = "ServiceAccount"
    name      = kubernetes_service_account_v1.tiller.metadata.0.name
    namespace = kubernetes_service_account_v1.tiller.metadata[0].namespace
  }
 subject {
    kind      = "User"
    name      = "admin"
    api_group = "rbac.authorization.k8s.io"
  }
  
  subject {
    kind      = "Group"
    name      = "system:masters"
    api_group = "rbac.authorization.k8s.io"
  }
}

resource "kubernetes_cluster_role_v1" "tiller" {
  metadata {
   name = "tiller-role-${kubernetes_namespace.environment.metadata.0.name}"
  }

  rule {
    api_groups = ["extensions","networking.k8s.io"]
    resources  = ["namespaces", "pods","ingresses","ingressclasses"]
    verbs      = ["get", "list", "watch"]
  }
}
