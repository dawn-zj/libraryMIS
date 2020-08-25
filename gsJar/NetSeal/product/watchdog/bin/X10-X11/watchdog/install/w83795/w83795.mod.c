#include <linux/module.h>
#include <linux/vermagic.h>
#include <linux/compiler.h>

MODULE_INFO(vermagic, VERMAGIC_STRING);

struct module __this_module
__attribute__((section(".gnu.linkonce.this_module"))) = {
 .name = KBUILD_MODNAME,
 .init = init_module,
#ifdef CONFIG_MODULE_UNLOAD
 .exit = cleanup_module,
#endif
};

static const struct modversion_info ____versions[]
__attribute_used__
__attribute__((section("__versions"))) = {
	{ 0xf8e3dbd2, "struct_module" },
	{ 0x5611e4ec, "param_get_bool" },
	{ 0xdc76cbcb, "param_set_bool" },
	{ 0x7a2a837d, "strict_strtol" },
	{ 0xc764027d, "i2c_register_driver" },
	{ 0x5b5c8dbd, "hwmon_device_register" },
	{ 0x47b26d11, "sysfs_chmod_file" },
	{ 0x59d8bc5a, "device_create_file" },
	{ 0x86cb9d9f, "__mutex_init" },
	{ 0x4f1c0ca7, "i2c_attach_client" },
	{ 0xc16fe12d, "__memcpy" },
	{ 0xef04d90c, "kmem_cache_zalloc" },
	{ 0x89892632, "malloc_sizes" },
	{ 0xe2d5255a, "strcmp" },
	{ 0x672144bd, "strlcpy" },
	{ 0xde0bdcff, "memset" },
	{ 0x58b38e65, "i2c_probe" },
	{ 0xecfab36e, "device_remove_file" },
	{ 0x23269a13, "strict_strtoul" },
	{ 0x7d11c268, "jiffies" },
	{ 0xa464d8ff, "mutex_unlock" },
	{ 0xd8eb2ab5, "mutex_lock" },
	{ 0x4ebfd82d, "i2c_smbus_read_byte_data" },
	{ 0x1d26aa98, "sprintf" },
	{ 0x465a850, "i2c_smbus_write_byte_data" },
	{ 0x37a0cba, "kfree" },
	{ 0x23062e64, "i2c_detach_client" },
	{ 0x2f97b54e, "hwmon_device_unregister" },
	{ 0xdd132261, "printk" },
	{ 0x2cc5eafc, "dev_driver_string" },
	{ 0x48f57e5a, "i2c_del_driver" },
};

static const char __module_depends[]
__attribute_used__
__attribute__((section(".modinfo"))) =
"depends=i2c-core,hwmon";


MODULE_INFO(srcversion, "17B7147EAD4069A7E1897DC");
