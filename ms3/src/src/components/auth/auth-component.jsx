import React from 'react';
import {connect} from 'react-redux'
import {registration, login} from './../../actions/auth-action'
import style from './style.scss'
import {browserHistory} from 'react-router'
import BeeIcon from './../../../res/img/bee.svg'
import PopupMessage from './../core/popup-message'


@connect((store) => {
	return {
		reg: store.reg,
		login: store.login
	}
})
export default class Auth extends React.Component {

	state = {
		showLoginForm: true,
		username: null,
		password: null,
		firstname: null,
		lastname: null,
		city: null,
		error: false,
		message: null
	};


	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
	}

	handleChange(event) {
		this.setState({[event.target.name]: event.target.value});
	}

	register() {

		if (this.state.username && this.state.password && this.state.firstname && this.state.lastname && this.state.city) {
			this.props.dispatch(registration(this.state.username, this.state.password, this.state.firstname, this.state.lastname, this.state.city)).then(() => {
				if (this.props.reg.success) {
					this.login()
				} else {
					this.setState({message: "User already exist"});
					this.changeErrorStatus();
				}
			})
		} else {
			this.setState({message: "All fields are mandatory!"});
			this.changeErrorStatus();
		}
		//
		// this.props.dispatch(registration("work234234234", "aaa", "fat", "last", "new szlavbuytwdef")).then(() => {
		// 	console.log(this.props);
		// })

	}


	getLoginHeader() {
		return <div>
			<div className="login-header">
				<img src={BeeIcon}/>
			</div>
			<div className="login-title">
				<h1>BusyBee</h1>
			</div>
		</div>
	}

	getLoginForm() {
		return <div className="form-action show">
			{this.getLoginHeader()}
			<form>
				<ul>
					<li>
						<input onChange={this.handleChange} type="text" name="username" placeholder="Username"/>
					</li>
					<li>
						<input onChange={this.handleChange} name="password" type="password" placeholder="Password"/>
					</li>
					<li>
						<input onClick={this.login.bind(this)} value="Login" className="button"/>
					</li>
				</ul>
			</form>
		</div>
	}

	getRegisterForm() {
		return <div id="register" className="form-action show">
			{this.getLoginHeader()}
			<form>
				<ul>
					<li>
						<input onChange={this.handleChange} name="username" type="text" placeholder="Username"/>
					</li>
					<li>
						<input onChange={this.handleChange} name="password" type="password" placeholder="Password"/>
					</li>
					<li>
						<input onChange={this.handleChange} name="firstname" type="text" placeholder="First Name"/>
					</li>
					<li>
						<input onChange={this.handleChange} name="lastname" type="text" placeholder="Last Name"/>
					</li>
					<li>
						<input onChange={this.handleChange} name="city" type="text" placeholder="City"/>
					</li>
					<li>
						<input onClick={this.register.bind(this)} value="Sign Up" className="button"/>
					</li>
				</ul>
			</form>
		</div>
	}

	changeState(state) {
		this.setState(
			{
				showLoginForm: state
			}
		)
	}

	login() {

		if(this.state.username === 'dummy') {
			localStorage.setItem("user", this.state.username);
			localStorage.setItem("id", 999);
			localStorage.setItem("firstname", 'John');
			localStorage.setItem("lastname", 'Doh');
			browserHistory.push('/todo');
		}

		if (this.state.username && this.state.password) {
			this.props.dispatch(login(this.state.username, this.state.password)).then(() => {

				if (!this.props.login.user) {
					this.setState({message: "Username or password is wrong!"})
					this.changeErrorStatus();
				} else {
					console.log(this.props);
					localStorage.setItem("user", this.state.username);
					localStorage.setItem("id", this.props.login.user.id);
					localStorage.setItem("firstname", this.props.login.user.firstname);
					localStorage.setItem("lastname", this.props.login.user.lastname);
					browserHistory.push('/todo');
				}


			})
		} else {
			this.setState({message: "All fields are mandatory!"})
			this.changeErrorStatus();
		}
	}

	changeErrorStatus() {
		this.setState({error: !this.state.error})
	}

	render() {
		return (
			<div>
				<div className="flat-form">
					<ul className="tabs">
						<li>
							<a className="active" onClick={this.changeState.bind(this, true)}>Login</a>
						</li>
						<li>
							<a onClick={this.changeState.bind(this, false)}>Register</a>
						</li>
					</ul>
					{this.state.showLoginForm ? this.getLoginForm() : this.getRegisterForm()}
				</div>
				{this.state.error ?
					<PopupMessage changeErrorStatus={this.changeErrorStatus.bind(this)} message={this.state.message}/> : null}
			</div>
		)
	}
}

