//
//  UsersViewController.swift
//  ios-app
//
//  Created by Eduardo on 06/07/2020.
//  Copyright © 2020 IceRock Development. All rights reserved.
//

import UIKit
import MultiPlatformLibrary

class UsersViewController: UIViewController {
    
    private var factory = SharedFactory()
    @IBOutlet var apiResultsTextView: UILabel!
    
    private var viewModel: UsersViewModel!
    
    override func viewDidLoad() {
    
        super.viewDidLoad()
        
        let a = factory.cache
        let b = factory.usersRepository
        let c = factory.authRepository
        
        viewModel = UsersViewModel(
            cache: a,
            userRepo: b,
            authRepo: c
        )
        
        viewModel.users.addObserver { [weak self] result in
            if (result != nil) {
                let users = result as! [DUser]
                self?.apiResultsTextView.text = self?.viewModel.parseUsers(users:users)
            }
        }
        
        viewModel.authResult.addObserver { [weak self] result in
            if (result != nil) {
                let authRes = result!
                self?.apiResultsTextView.text = self?.viewModel.parseResponse(res: authRes)
            }
        }
    }
    
    private let fakeUser = DUser(id:2, email:"test@konet.com", first_name:"Konet")
    private let fakeAuth = DAuthRequest(email: "eve.holt@reqres.in", password: "cityslicka")
    
    @IBAction func getUsersClick(_ sender: Any) {
        
        self.viewModel.getAllUsers()
    }
    
    @IBAction func getUsersPageClick(_ sender: Any) {
        
        self.viewModel.getUsersPage(page: 2)
    }
    
    @IBAction func getUserClick(_ sender: Any) {
        
        self.viewModel.getUser(id: 2)
    }
    
    @IBAction func postUsersClick(_ sender: Any) {
        
        self.viewModel.postUser(newUser: self.fakeUser)
    }
    
    @IBAction func putUsersClick(_ sender: Any) {
        
        self.viewModel.putUser(user: self.fakeUser)
    }
    
    @IBAction func deleteUserClick(_ sender: Any) {
        
        self.viewModel.deleteUser(id: 2)
    }
    
    @IBAction func loginClick(_ sender: Any) {
        
        self.viewModel.login(auth: self.fakeAuth)
    }
}
