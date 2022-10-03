#
# Copyright (C)2022 Marco Cavallini - KOAN sas - <https://koansoftware.com>
#

DESCRIPTION = "SYNwall kernel module out of the kernel tree"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/SYNwall/SYNwall;protocol=https;branch=master"
SRC_URI += "file://Makefile"

PV = "1.0+git${SRCPV}"
SRCREV = "d3495ad3ef9fa80dc4478aa535fa8b84da848272"

S = "${WORKDIR}/git"

inherit module

# Replace the existing Makefile wit one suitable for Yocto Project
do_compile_prepend() {
    cp ${WORKDIR}/Makefile ${S}
}

EXTRA_OEMAKE_append_task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNEL=${STAGING_KERNEL_DIR}"

RDEPENDS_${PN} = "kernel-modules iptables conntrack-tools"
