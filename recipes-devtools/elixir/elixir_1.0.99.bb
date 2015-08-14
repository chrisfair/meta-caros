HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications"
DESCRIPTION = "Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "git://github.com/elixir-lang/elixir;protocol=git;name=elixir;destsuffix=git-elixir"
SRCREV_elixir = "1d2f074a45a0c8945f860555c2d24df8d1110c1a"

PV_hex = "0.7.5"
SRC_URI += " https://github.com/hexpm/hex/archive/v${PV_hex}.tar.gz;name=hex"

# SRC_URI[elixir.md5sum] = "7e1e88e012d93ea143188a7b17c52917"
# SRC_URI[elixir.sha256sum] = "79341fde3b01217aa252b8996d1b1b27cd1006cb89fe43446d5241e3dcb84bad"

SRC_URI[hex.md5sum] = "052ba46cfb602032237718bcf8bad96d"
SRC_URI[hex.sha256sum] = "22403952073cee120894a50fe26babd76d6ce3d7f21d08d34973a8c21d012c14"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang"

S = "${WORKDIR}/git-elixir"

do_install() {
   oe_runmake install PREFIX=${D}${prefix}

   export PATH="${D}${bindir}:$PATH"
   cd "${WORKDIR}/hex-${PV_hex}"
   MIX_ENV=release mix compile
   cp -rv _build/release/lib/hex ${D}${libdir}/hex/
}

FILES_${PN} = "${bindir} ${libdir}"

BBCLASSEXTEND = "native"
