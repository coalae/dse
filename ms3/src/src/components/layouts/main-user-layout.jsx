import React from 'react'
import {browserHistory} from 'react-router'
import style from './style.scss'

export default class MainUserLayout extends React.Component {
	constructor(props) {
		super(props);
	}


	getNavigation(){
		return <header className="sticky">
			<div className="container-navigation-head">
				<h1 onClick={() => browserHistory.push('/todo')} className="title">BusyBee</h1>
				<ul>
					<li onClick={() => browserHistory.push('/todo/create')}><span>Create new List</span></li>
					<li onClick={this.logout.bind(this)}><span>Logout</span></li>
				</ul>
			</div>

		</header>
	}

	logout(){
		localStorage.removeItem("id");
		localStorage.removeItem("user");
		browserHistory.push('/')
	}

	render() {
		return (
			<div>
				{this.getNavigation()}
				<div className="main-content-container">
					{React.cloneElement(this.props.children)}
				</div>
			</div>
		)
	}
}
