// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "DemoKmm",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "DemoKmm",
            targets: ["DemoKmm"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "DemoKmm",
            path: "./DemoKmm.xcframework"
        ),
    ]
)
