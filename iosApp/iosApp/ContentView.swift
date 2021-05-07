import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greeting()
    let driver = DriverFactory()
    let vm = TodoViewModel(usecase: TodoUseCase(repo: TodoRepository(database: TodoDatabase(driverFactory:DriverFactory()), api: Api())))
    
    
    var body: some View {
        SizeView(viewModel: vm)
    }
}

struct SizeView: View {
    @ObservedObject var viewModel: TodoViewModel
    
    var body: some View {
        Text(String(viewModel.size)).onAppear(perform: {
            viewModel.startObservingPeopleUpdates()
        }).onDisappear {
            viewModel.stopObservingPeopleUpdates()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
