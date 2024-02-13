terraform {

  backend "s3" {
    bucket         = "jwt-verify-api-backend"
    key            = "terraform.tfstate"
    region         = "us-east-2"
  }

  required_providers {
    aws = {
      source  = "aws"
      version = "~> 5.7.0"
    }

    random = {
      source  = "random"
      version = "~> 3.5.1"
    }

    tls = {
      source  = "tls"
      version = "~> 4.0.4"
    }

    cloudinit = {
      source  = "cloudinit"
      version = "~> 2.3.2"
    }
  }

  required_version = "~> 1.3"
}