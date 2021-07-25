REGISTRY 	:= kenshin579
APP    		:= rasberrypi
TAG         := java-multi-arch-hello
IMAGE       := $(REGISTRY)/$(APP):$(TAG)

.PHONY: docker-push
docker-push:
	@docker buildx build \
	--platform linux/amd64,linux/arm64,linux/arm/v7,linux/arm/v6 \
	-t $(IMAGE) --push -f Dockerfile .
