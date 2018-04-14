import React from 'react';
import {connect} from 'react-redux'
import {browserHistory} from 'react-router'
import style from './style.scss'
import {getListsByUserId, createList} from './../../actions/lists-action'
import CreateIcon from './../../../res/img/honeycomb.svg'
import BeeIcon from './../../../res/img/bee.svg'

@connect((store) => {
	return {
		userById: store.userById,
		createList: store.createList
	}
})
export default class CreateToDoListComponent extends React.Component {

	state = {
		category_id: null,
		name: null
	};

	constructor(props) {
		super(props);


		if (!localStorage.getItem("id")) {
			browserHistory.push('/');
		} else {
			this.props.dispatch(getListsByUserId(localStorage.getItem("id"))).then(() => {
				console.log(this.props);
			})
		}

		this.handleChange = this.handleChange.bind(this);

	}

	getHeader() {
		return <div className="header-container">
			<div className="header">
				<img src={BeeIcon}/>
				<div className="todo-header-title">
					<h1>BusyBee</h1>
				</div>
			</div>
		</div>
	}

	getCategories() {
		let align = {textAlign: 'center'};
		return <div className="categories-list">
			<ul>
				<li style={align} onClick={this.setCategoryId.bind(this, 2)}>Very Important</li>
				<li style={align} onClick={this.setCategoryId.bind(this, 1)}>Important</li>
				<li style={align} onClick={this.setCategoryId.bind(this, 3)}>Not Important</li>
			</ul>
		</div>
	}

	nameListComponent() {
		return <div>
			<div className="add-task">
				<input onChange={this.handleChange} name='name' placeholder="List Name"/>
				<img onClick={this.addNewItem.bind(this)} src={CreateIcon}/>
			</div>
		</div>
	}

	addNewItem() {
		let reqParams = {
			user_id: localStorage.getItem("id"),
			name: this.state.name,
			category_id: this.state.category_id
		};

		this.props.dispatch(createList(reqParams)).then(() => {
			browserHistory.push('/todo/list/' + this.props.createList.list.insertId);
		})
	}

	handleChange(event) {
		this.setState({[event.target.name]: event.target.value});
	}


	setCategoryId(category_id) {
		this.setState({category_id: category_id})
	}

	render() {
		return (
			<div className="todo-container">
				<div className="content">
					{this.getHeader()}
					<div className="container">
						<div className="container-holder">
							{this.state.category_id ? this.nameListComponent() : this.getCategories()}
						</div>
					</div>
				</div>
			</div>
		)
	}
}

