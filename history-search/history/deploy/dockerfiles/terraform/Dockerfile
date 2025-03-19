FROM golang:1.23-alpine
MAINTAINER "HashiCorp Terraform Team <terraform@hashicorp.com>"

ENV TERRAFORM_VERSION=1.5

RUN apk add --update git bash openssh make musl-dev coreutils

RUN apk update && apk add --no-cache gcc

ENV TF_DEV=true
ENV TF_RELEASE=true

WORKDIR $GOPATH/src/github.com/hashicorp/terraform
RUN git clone https://github.com/hashicorp/terraform.git ./ && \
    git checkout v${TERRAFORM_VERSION} && \
    /bin/bash scripts/build.sh

WORKDIR $GOPATH

COPY --from=lachlanevenson/k8s-kubectl:v1.24.8 /usr/local/bin/kubectl /usr/local/bin/kubectl
RUN git clone https://github.com/gavinbunney/terraform-provider-kubectl.git
RUN cd terraform-provider-kubectl && make build

WORKDIR /terraform
