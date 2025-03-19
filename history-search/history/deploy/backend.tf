terraform {
  backend "gcs" {
    project = "project id" 
    bucket  = "bucket namee"
    prefix  = "terraform/kubernetes"
    credentials = "service account.json"
  }
}
