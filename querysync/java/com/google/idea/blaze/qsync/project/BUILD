load("@protobuf//bazel:java_proto_library.bzl", "java_proto_library")
load("@protobuf//bazel:proto_library.bzl", "proto_library")
load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library")

package(default_visibility = [
    "//querysync:__subpackages__",
    "//third_party/google/aswb/java/com/google/devtools/intellij/blaze/plugin/aswb:__pkg__",
    "//third_party/google/aswb/javatests/com/google/devtools/intellij/blaze/plugin/aswb:__pkg__",
])

proto_library(
    name = "language_class_proto",
    srcs = ["language_class.proto"],
)

java_proto_library(
    name = "language_class_java_proto",
    visibility = [
        "//base:__subpackages__",
        "//cpp:__subpackages__",
        "//querysync:__subpackages__",
        "//third_party/google/aswb/javatests/com/google/devtools/intellij/blaze/plugin/aswb:__pkg__",
    ],
    deps = [":language_class_proto"],
)

proto_library(
    name = "project_proto",
    srcs = ["project.proto"],
    deps = [":language_class_proto"],
)

java_proto_library(
    name = "project_java_proto",
    visibility = [
        "//base:__subpackages__",
        "//cpp:__subpackages__",
        "//querysync:__subpackages__",
    ],
    deps = [":project_proto"],
)

proto_library(
    name = "snapshot_proto",
    srcs = ["snapshot.proto"],
    deps = [
        ":language_class_proto",
        "//querysync/java/com/google/idea/blaze/qsync/query:querysummary",
    ],
)

java_proto_library(
    name = "snapshot_java_proto",
    visibility = ["//querysync:__subpackages__"],
    deps = [":snapshot_proto"],
)

kt_jvm_library(
    name = "project",
    srcs = glob(["*.java"]),
    deps = [
        ":language_class_java_proto",
        ":project_java_proto",
        ":snapshot_java_proto",
        "//querysync/java/com/google/idea/blaze/qsync/query",
        "//querysync/java/com/google/idea/blaze/qsync/query:querysummary_java_proto",
        "//shared",
        "//shared:vcs",
        "//third_party/java/auto_value",
        "@com_google_guava_guava//jar",
        "@error_prone_annotations//jar",
        "@jsr305_annotations//jar",
        "@protobuf//:protobuf_java",
    ],
)
