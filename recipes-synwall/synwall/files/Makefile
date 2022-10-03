obj-m += SYNwall.o

SRC := $(shell pwd)

SYNwall-objs += SYNwall_netfilter.o SYNquark.o SYNauth.o SYNhelpers.o

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install
