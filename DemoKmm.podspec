Pod::Spec.new do |spec|
  spec.name = 'DemoKmm'
  spec.version = '1.0.0'
  spec.homepage = 'https://www.cocoapods.org'
  spec.source = { :git => "git@github.com:mjohnki/DemoKMM.git", :tag => "#{spec.version}" }
  spec.authors = 'Martin Johnki'
  spec.license = { :type => "MIT", :file => "LICENSE" }
  spec.summary = 'KMM shared code pod'
  spec.static_framework = true
  spec.vendored_frameworks = "DemoKmm.xcframework"
  spec.libraries = "c++"
  spec.module_name = "#{spec.name}_umbrella"
  spec.ios.deployment_target = '11.0'
end