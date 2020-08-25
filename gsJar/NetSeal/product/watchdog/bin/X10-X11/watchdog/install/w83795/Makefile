# For building for the current running version of Linux
TARGET		:= $(shell uname -r)
# Or specific version
#TARGET		:= 2.6.36.1
#TARGET		:= 2.6.35.9
#TARGET		:= 2.6.34.4
#TARGET		:= 2.6.33.7
#TARGET		:= 2.6.32.25
#TARGET		:= 2.6.30.10
#TARGET		:= 2.6.27.46
#TARGET		:= 2.6.26.8
#TARGET		:= 2.6.25.20
#TARGET		:= 2.6.22.19
#TARGET		:= 2.6.18.8
#TARGET		:= 2.6.16.46

KERNEL_MODULES	:= /lib/modules/$(TARGET)
KERNEL_BUILD	:= $(KERNEL_MODULES)/build
SYSTEM_MAP	:= $(shell if test -r /boot/System.map-$(TARGET) ; then echo /boot/System.map-$(TARGET) ; else echo $(KERNEL_BUILD)/System.map ; fi)

DRIVER := w83795

# Directory below /lib/modules/$(TARGET)/kernel into which to install
# the module:
MOD_SUBDIR = drivers/hwmon

obj-m	:= $(DRIVER).o

MAKEFLAGS	+= --no-print-directory
EXTRA_CFLAGS	+= -DDEBUG
# Comment out the following if you don't want to include fan control support
EXTRA_CFLAGS	+= -DCONFIG_SENSORS_W83795_FANCTRL

.PHONY: all install modules modules_install clean

all: modules

# Targets for running make directly in the external module directory:
modules clean:
	@$(MAKE) -C $(KERNEL_BUILD) M=$(CURDIR) $@

install: modules_install

modules_install:
	test -d $(KERNEL_MODULES)/kernel/$(MOD_SUBDIR) || mkdir $(KERNEL_MODULES)/kernel/$(MOD_SUBDIR)
	cp $(DRIVER).ko $(KERNEL_MODULES)/kernel/$(MOD_SUBDIR)
	depmod -a -F $(SYSTEM_MAP) $(TARGET)
