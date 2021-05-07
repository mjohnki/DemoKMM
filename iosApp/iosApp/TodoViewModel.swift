//
//  Collector.swift
//  iosApp
//
//  Created by Martin Johnki on 05.05.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

class TodoViewModel: ObservableObject {
    @Published var size = -1
    
    private var closeable: RuntimeCloseable?
    private var flow: CommonFlow<NSArray>
    
    private let usecase: TodoUseCase
    init(usecase: TodoUseCase) {
        self.usecase = usecase
        
        flow = usecase.getTodosCommonFlow()
    
    }
    
    func startObservingPeopleUpdates() {
        closeable = flow.watch(block: { data in
            if data != nil {
                self.size = data!.count;
            }
        });
    }
    
    func stopObservingPeopleUpdates() {
        closeable!.close()
    }
}
