'use strict';

import React from 'react'
import {render} from 'react-dom'
import {Router, Route, Link, browserHistory} from 'react-router'
import {Provider} from "react-redux"
import store from './store'

import Main from './components/main/main'
import Auth from './components/auth/auth-component'
import ToDoMainComponent from './components/todo/todo-main-component'
import ToDoListComponent from './components/todo/todo-list-component'
import CreateToDoListComponent from './components/todo/create-new-list-component'
import MainUserLayout from './components/layouts/main-user-layout'
import style from './../styles/index.scss'


render((
	<Provider store={store}>
		<Router history={browserHistory}>
			<Route path="/" component={Auth}/>
			<Route component={MainUserLayout}>
				<Route path="/todo" component={ToDoMainComponent}/>
				<Route path="/todo/list/:id" component={ToDoListComponent}/>
				<Route path="/todo/create" component={CreateToDoListComponent}/>
			</Route>
		</Router>
	</Provider>
), document.getElementById('app'));
