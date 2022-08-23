terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  profile = "default"
  region  = "ap-northeast-2"
}

resource "aws_instance" "dev_service_server" {
  ami                    = "ami-0ea5eb4b05645aa8a"
  instance_type          = "t2.medium"
  key_name               = aws_key_pair.pk_key_pair.key_name
  vpc_security_group_ids = [aws_security_group.dev_security_group.id, aws_security_group.dev_service_security_group.id]
  user_data              = file("setup-service.sh")

  tags = {
    Name = "DevNginxServer"
  }
}

resource "aws_instance" "dev_api_server" {
  ami                    = "ami-0ea5eb4b05645aa8a"
  instance_type          = "t2.medium"
  key_name               = aws_key_pair.pk_key_pair.key_name
  vpc_security_group_ids = [aws_security_group.dev_security_group.id, aws_security_group.dev_api_security_group.id, aws_security_group.dev_service_security_group.id]
  user_data              = file("setup-api.sh")

  tags = {
    Name = "DevAppServer"
  }
}

resource "aws_instance" "dev_mariadb_server" {
  ami                    = "ami-0ea5eb4b05645aa8a"
  instance_type          = "t2.medium"
  key_name               = aws_key_pair.pk_key_pair.key_name
  vpc_security_group_ids = [aws_security_group.dev_security_group.id, aws_security_group.dev_mariadb_security_group.id]
  user_data              = file("setup-database.sh")

  tags = {
    Name = "DevMariadbServer"
  }
}

resource "aws_instance" "dev_flask_server" {
  ami                    = "ami-0ea5eb4b05645aa8a"
  instance_type          = "t2.medium"
  key_name               = aws_key_pair.pk_key_pair.key_name
  vpc_security_group_ids = [aws_security_group.dev_security_group.id, aws_security_group.dev_flask_security_group.id]
  user_data              = file("setup-flask.sh")

  tags = {
    Name = "DevFlaskServer"
  }
}

resource "aws_instance" "dev_jupyter_server" {
  ami                    = "ami-0ea5eb4b05645aa8a"
  instance_type          = "t2.medium"
  key_name               = aws_key_pair.pk_key_pair.key_name
  vpc_security_group_ids = [aws_security_group.dev_security_group.id, aws_security_group.dev_service_security_group.id, aws_security_group.dev_jupyter_security_group.id]
  user_data              = file("setup-flask.sh")

  tags = {
    Name = "DevJupyterServer"
  }
}

output "dev_api_server_public_ip" {
  description = "Public IP address of DevNginxServer"
  value       = aws_instance.dev_api_server.public_ip
}

output "dev_service_server_public_ip" {
  description = "Public IP address of DevAppServer"
  value       = aws_instance.dev_service_server.public_ip
}

output "dev_mariadb_server_public_ip" {
  description = "Public IP address of DevMariadbServer"
  value       = aws_instance.dev_mariadb_server.public_ip
}

output "dev_flask_server_public_ip" {
  description = "Public IP address of DevFlaskServer"
  value       = aws_instance.dev_flask_server.public_ip
}

output "dev_jupyter_server_public_ip" {
  description = "Public IP address of DevJupyterServer"
  value       = aws_instance.dev_jupyter_server.public_ip
}

resource "aws_security_group" "dev_security_group" {
  egress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "to all"
      from_port        = 0
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "-1"
      security_groups  = []
      self             = false
      to_port          = 0
    }
  ]

  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "ssh"
      from_port        = 22
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 22
    }
  ]
}

resource "aws_security_group" "dev_api_security_group" {
  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "Spring default port"
      from_port        = 8080
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 8080
    }
  ]
}

resource "aws_security_group" "dev_service_security_group" {
  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "http"
      from_port        = 80
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 80
    },
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "https"
      from_port        = 443
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 443
    }
  ]
}

resource "aws_security_group" "dev_mariadb_security_group" {
  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "db tcp connection"
      from_port        = 3306
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 3306
    }
  ]
}

resource "aws_security_group" "dev_flask_security_group" {
  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "flask tcp connection"
      from_port        = 5000
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 5000
    }
  ]
}

resource "aws_security_group" "dev_jupyter_security_group" {
  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0", ]
      description      = "jupyter tcp connection"
      from_port        = 8888
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 8888
    }
  ]
}

variable "generated_key_name" {
  type        = string
  default     = "pk-key"
  description = "Key-pair generated by Terraform"
}

resource "tls_private_key" "ssh" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_key_pair" "pk_key_pair" {
  key_name   = "pk-key"
  public_key = tls_private_key.ssh.public_key_openssh

  provisioner "local-exec" {
    command = <<-EOT
      echo '${tls_private_key.ssh.private_key_pem}' > ./'${var.generated_key_name}'.pem
      chmod 400 ./'${var.generated_key_name}'.pem
    EOT
  }
}

