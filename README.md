# meta-SYNwall

This README file contains information on the contents of the meta-synwall layer.

Please see the corresponding sections below for details.


# Dependencies

This layer depends on:

  URI: git://git.openembedded.org/bitbake

  URI: git://git.openembedded.org/openembedded-core

  URI: git://git.yoctoproject.org/git/meta-yocto

  URI: git://git.openembedded.org/meta-openembedded

  URI: git://git.openembedded.org/meta-openembedded/meta-python

  URI: git://git.openembedded.org/meta-openembedded/meta-networking


# Patches

Please submit any patches against the `meta-synwall` layer by using the GitHub pull-request feature.  Fork the repo, make a branch, do the work, rebase from upstream, create the pull request, yada-yada.


# Maintainers

This layer is maintained by KOAN <https://koansoftware.com>

The repository is : https://github.com/SYNwall/meta-synwall


# Building

Add the needed layers to `bblayers.conf`

```bash
meta-openembedded/meta-oe \
meta-openembedded/meta-python \
meta-openembedded/meta-networking \
```

Add the recipe `synwall` to the final image file or in the `local.conf`

```bash
IMAGE_INSTALL_append = " synwall"
```

And build the image (for example `core-image-minimal`)


# Execution and testing

*Tested on a beaglebone-yocto MACHINE*

On the target system set up the iptable rule:

```bash
iptables -A OUTPUT -m conntrack -p udp --ctstate NEW,RELATED,ESTABLISHED -j ACCEPT
```

And load the module (for example):

```bash
cd /lib/modules/5.4.58-yocto-standard/extra/

insmod SYNwall.ko \
  psk=123456789012345678901234567890123 precision=11 \
  portk=12,13,14,15,16 load_delay=10000 enable_udp=1

[   57.028114] SYNwall: loading out-of-tree module taints kernel.
[   57.031350] SYNwall: Waiting for random pool initialization...
[   57.031658] SYNwall: Injecting module v0.5.0...(load_delay=10000 (ms), precision=10, disable_out=0, enable_antidos=0, enable_antispoof=0, enable_udp=1)
[   57.032050] SYNwall: Port Knocking enabled
```

Verify the loaded modules

```bash
lsmod

    Tainted: G  
SYNwall 24576 0 - Live 0xbf01c000 (O)
iptable_filter 16384 1 - Live 0xbf04e000
xt_conntrack 16384 2 - Live 0xbf046000
nf_conntrack 94208 1 xt_conntrack, Live 0xbf023000
nf_defrag_ipv6 16384 1 nf_conntrack, Live 0xbf007000
nf_defrag_ipv4 16384 1 nf_conntrack, Live 0xbf017000
ip_tables 24576 1 iptable_filter, Live 0xbf00c000
x_tables 24576 3 iptable_filter,xt_conntrack,ip_tables, Live 0xbf000000
```

Test can be performed using an SSH connection to the counterpart

```bash
ssh root@192.168.0.184
```

# License

All metadata is MIT licensed unless otherwise stated. Source code and
binaries included in tree for individual recipes is under the LICENSE
stated in each recipe (.bb file) unless otherwise stated.


------

*This README document is Copyright (C)2022 KOAN sas - Bergamo, Italia - <https://koansoftware.com>*
