#
# Description: Builds ijwb
#

load("@rules_java//java:defs.bzl", "java_library")
load(
    "//:build-visibility.bzl",
    "IJWB_PACKAGES_VISIBILITY",
    "PLUGIN_PACKAGES_VISIBILITY",
)
load("//:version.bzl", "VERSION")
load(
    "//build_defs:build_defs.bzl",
    "intellij_plugin",
    "intellij_plugin_library",
    "plugin_deploy_zip",
    "repackaged_files",
    "stamped_plugin_xml",
)
load(
    "//testing:test_defs.bzl",
    "intellij_integration_test_suite",
    "intellij_unit_test_suite",
)

licenses(["notice"])

intellij_plugin_library(
    name = "plugin_library",
    plugin_xmls = ["src/META-INF/plugin.xml"],
    visibility = PLUGIN_PACKAGES_VISIBILITY,
    deps = [":otel_lib"],
)

stamped_plugin_xml(
    name = "stamped_plugin_xml",
    plugin_id = "com.snowflake.telemetry",
    plugin_name = "OTEL exporter extension",
    # #api212: We depend on an API which is only contained in 2021.2.1+.
    since_build_numbers = {"212": "212.5080.55"},
    stamp_since_build = True,
    stamp_until_build = True,
    version = VERSION,
)

java_library(
    name = "opentelemetry-exporter-otlp-common",
    visibility = ["//visibility:public"],
    exports = [
        ":opentelemetry-exporter-otlp-common-1_47_0_http_import",
        ":opentelemetry-exporter-common-1_47_0_http_import",
        ":opentelemetry-exporter-otlp-1_47_0_http_import",
        ":opentelemetry-sdk-1_47_0_http_import",
        ":opentelemetry-sdk-extension-autoconfigure-spi-1_47_0_http_import",
        ":opentelemetry-exporter-sender-grpc-managed-channel-1_47_0_http_import",
    ],
)

java_import(
    name = "opentelemetry-exporter-otlp-common-1_47_0_http_import",
    jars = ["@opentelemetry-exporter-otlp-common-1_47_0_http//file"],
)

java_import(
    name = "opentelemetry-exporter-common-1_47_0_http_import",
    jars = ["@opentelemetry-exporter-common-1_47_0_http//file"],
)

java_import(
    name = "opentelemetry-exporter-otlp-1_47_0_http_import",
    jars = ["@opentelemetry-exporter-otlp-1_47_0_http//file"],
)

java_import(
    name = "opentelemetry-exporter-sender-jdk-1_47_0_http_import",
    jars = ["@opentelemetry-exporter-sender-jdk-1_47_0_http//file"],
)

java_import(
    name = "opentelemetry-exporter-sender-grpc-managed-channel-1_47_0_http_import",
    jars = ["@opentelemetry-exporter-sender-grpc-managed-channel-1_47_0_http//file"],
)

java_import(
    name = "opentelemetry-sdk-1_47_0_http_import",
    jars = ["@opentelemetry-sdk-1_47_0_http//file"],
)

java_import(
    name = "opentelemetry-sdk-extension-autoconfigure-spi-1_47_0_http_import",
    jars = ["@opentelemetry-sdk-extension-autoconfigure-spi-1_47_0_http//file"],
)

java_library(
    name = "otel_lib",
    srcs = glob(["src/**/*.java"]),
    visibility = IJWB_PACKAGES_VISIBILITY,
    deps = [
        ":opentelemetry-exporter-otlp-common",
        "@maven//:io_grpc_grpc_stub",
        "@maven//:io_grpc_grpc_api",
        "@maven//:io_grpc_grpc_core",
        "@maven//:io_grpc_grpc_netty",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
    ],
)

intellij_plugin(
    name = "otel_bazel",
    plugin_icons = ["//common:pluginIcon.svg"],
    plugin_xml = ":stamped_plugin_xml",
    tags = [
        "incomplete-deps",  # remove this suppression and add any missing deps, see go/java-import-deps-checking-lsc
    ],
    deps = [
        ":plugin_library",
    ],
)

repackaged_files(
    name = "plugin_jar",
    srcs = [":otel_bazel"],
    prefix = "otel/lib",
)

plugin_deploy_zip(
    name = "otel_bazel_zip",
    srcs = [
        ":plugin_jar",
    ],
    zip_filename = "otel_bazel.zip",
)
