import React from 'react';
import {connect} from 'react-redux'
import {browserHistory, Link} from 'react-router'
import style from './style.scss'
import {getListsByUserId} from './../../actions/lists-action'
import AddIcon from './../../../res/img/honey.svg'
import BeeIcon from './../../../res/img/bee.svg'
import VeryImpIcon from './../../../res/img/bee_color.svg'
import ImpIcon from './../../../res/img/bee_white_filled.svg'
import NotImpIcon from './../../../res/img/bee_white.svg'

const dummyList = [{
	"name" : 'test',
	"id": 888,
	created: null,
	catname: "Very Important",
	catid: 2
},
	{
		"name" : 'test not',
		"id": 889,
		created: null,
		catname: "Not Important",
		catid: 3
	}];

@connect((store) => {
	return {
		userById: store.userById
	}
})
export default class ToDoMainComponent extends React.Component {

	constructor(props) {
		super(props);


		if (!localStorage.getItem("id")) {
			browserHistory.push('/');
		} else {
			if(localStorage.getItem('user') === 'dummy') {
				this.state = {
						lists: dummyList
				}
			} else {
				this.props.dispatch(getListsByUserId(localStorage.getItem("id"))).then(() => {
					console.log(this.props);
				})
			}
		}

	}

	getHeader() {
		return <div className="header-container">
			<div className="header">
				<img src={BeeIcon}/>
				<div className="todo-header-title">
					<h1>Bzzzz, {localStorage.getItem("firstname")} {localStorage.getItem("lastname")}</h1>
				</div>
			</div>
		</div>
	}

	getNoListsComponent() {
		let h1titleStyle = {color: 'white'};
		return <div>
			<h1 style={h1titleStyle}>You don't have any todo lists yet</h1>
			<div onClick={this.goToCreate.bind(this)} className="button-list">
				<p>Create todo list</p>
			</div>
		</div>
	}

	goToCreate() {
		browserHistory.push('/todo/create')
	}

	getVeryImportantLists(lists) {
		return <div className="pure-u-1-3">
			<div className='img-container'>
				<img src={VeryImpIcon}/>
			</div>
			<p>Very Important</p>
			<ul>
				{lists.filter(list => list.catname === "Very Important").map(list => {
					return <li onClick={this.goToList.bind(this, list.id)} key={list.id}>{list.name}</li>
				})}
			</ul>
		</div>
	}

	getImportantLists(lists) {
		return <div className="pure-u-1-3">
			<div className='img-container'>
				<img src={ImpIcon}/>
			</div>
			<p>Important</p>
			<ul>
				{lists.filter(list => list.catname === "Important").map(list => {
					return <li onClick={this.goToList.bind(this, list.id)} key={list.id}>{list.name}</li>
				})}
			</ul>
		</div>
	}

	getNotImportantLists(lists) {
		return <div className="pure-u-1-3">
			<div className='img-container'>
				<img src={NotImpIcon}/>
			</div>
			<p>Not Important</p>
			<ul>
				{lists.filter(list => list.catname === "Not Important").map(list => {
					return <li onClick={this.goToList.bind(this, list.id)} key={list.id}>{list.name}</li>
				})}
			</ul>
		</div>
	}

	goToList(listId) {
		browserHistory.push("/todo/list/" + listId)
	}

	render() {
		return (
			<div className="todo-container">
				<div className="content">
					{this.getHeader()}
					<div className="container">
						<div className="container-holder-full">
							{localStorage.getItem('user') !== 'dummy' && this.props.userById.done && this.props.userById.lists && this.props.userById.lists.length < 1 ? this.getNoListsComponent() : null}
							{/*{this.getCategoriesBlock()}*/}


							{localStorage.getItem('user') !== 'dummy' && this.props.userById.done && this.props.userById.lists && this.props.userById.lists.length > 0 ?
								<div className="pure-g category-container">
									{this.getVeryImportantLists(this.props.userById.lists)}
									{this.getImportantLists(this.props.userById.lists)}
									{this.getNotImportantLists(this.props.userById.lists)}
								</div> : null}

							{localStorage.getItem('user') === 'dummy' ?
								<div className="pure-g category-container">
									{this.getVeryImportantLists(this.state.lists)}
									{this.getImportantLists(this.state.lists)}
									{this.getNotImportantLists(this.state.lists)}
								</div> : null}
						</div>
					</div>
				</div>
			</div>
		)
	}
}

