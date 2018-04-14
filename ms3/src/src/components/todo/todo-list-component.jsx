import React from 'react';
import {connect} from 'react-redux'
import {browserHistory, Link} from 'react-router'
import style from './style.scss'
import {getListById, createItem, getItemsByListId, updateItemById, deleteItemById, updateList, deleteList} from './../../actions/lists-action'
import AddIcon from './../../../res/img/honey.svg'
import BeeIcon from './../../../res/img/bee.svg'
import EditIcon from './../../../res/img/edit.svg'
import SaveIcon from './../../../res/img/circle-with-check-symbol.svg'
import DeleteIcon from './../../../res/img/dustbin.svg'

const dummyItems = [{
		id: 999,
		name: 'dummy thing one',
		list_id: 888
},{
	id: 998,
	name: 'dummy thing two',
	list_id: 888
}];

@connect((store) => {
	return {
		listById: store.listById,
		items: store.items,
		updateList: store.updateList
	}
})
export default class ToDoListComponent extends React.Component {

	state = {
		currentItem: null,
		editedName: null,
		items: null,
		selectedItem: null,
		editedListName: null,
		isListEdit: false
	};

	constructor(props) {
		super(props);

			if(localStorage.getItem('user') !== 'dummy') {
			this.props.dispatch(getListById(this.props.params.id)).then(() => {
				this.setState({editedListName: this.props.listById.list[0].name})
			});
			this.props.dispatch(getItemsByListId(this.props.params.id)).then(() => {
				this.setState({items: this.props.items.items});
				console.log(this.state);
			});
		}


		this.handleChange = this.handleChange.bind(this);

	}

	componentWillMount() {
		if(localStorage.getItem('user') === 'dummy') {
			this.setState({editedListName: 'dummy test list name'})
			this.setState({items: dummyItems});
		}
	}

	handleChange(event) {
		this.setState({[event.target.name]: event.target.value});
	}

	getHeader() {
		return <div className="header-container">
			<div className="header">
				<img onClick={() => browserHistory.push('/todo')} src={BeeIcon}/>
				<div className="todo-header-title">

					{this.state.isListEdit ?
						<div>
							<input onChange={this.handleChange} className="edit-input" name="editedListName"
										 defaultValue={this.state.editedListName}/>
							<img onClick={this.performEditingListName.bind(this)} src={SaveIcon}/>
						</div> :
						<div>
							<h1>{this.state.editedListName}</h1>
							<img onClick={this.initEditListName.bind(this)} src={EditIcon}/>
							<img onClick={this.deleteListForever.bind(this)} src={DeleteIcon}/>
						</div>}
				</div>
			</div>
		</div>
	}

	deleteListForever(){
		if(localStorage.getItem('user') === 'dummy') {
			browserHistory.push('/todo');
		} else {
			this.props.dispatch(deleteList(this.props.listById.list[0].id)).then(() => {
				browserHistory.push('/todo');
			});
		}
	}

	performEditingListName(){
		this.setState({isListEdit: !this.state.isListEdit});
		let updateData = {
			name: this.state.editedListName
		};
		if(localStorage.getItem('user') !== 'dummy') {
			this.props.dispatch(updateList(updateData, this.props.listById.list[0].id)).then(() => {
				// location.reload();
			});
		}

	}

	initEditListName() {
		this.setState({isListEdit: !this.state.isListEdit})
	}

	getAddInput() {
		return <div className="add-task">
			<input onChange={this.handleChange} name='currentItem' placeholder="Add Task"/>
			<img onClick={this.addItemToList.bind(this)} src={AddIcon}/>
		</div>
	}

	addItemToList() {
		let item = {
			name: this.state.currentItem,
			done: 0,
			list_id: this.props.params.id
		};

		if(localStorage.getItem('user') === 'dummy') {
			let array = this.state.items;
			array.push(item);
			this.setState({items: array})
		} else {
			this.props.dispatch(createItem(item)).then(() => {
				this.props.dispatch(getItemsByListId(this.props.params.id)).then(() => {
					this.setState({items: this.props.items.items})
				})
			})
		}
	}

	updateItemById(item) {
		this.props.dispatch(updateItemById(item, item.id)).then(() => {
			console.log(this.state);
			console.log(this.props);
		})

	}

	handleChange(event) {
		this.setState({[event.target.name]: event.target.value});
	}

	showListOfItems() {
		return <selection>
			<ul>
				{this.state.items.map(item => {
					let doneStyle = item.done ? {textDecoration: 'line-through'} : null;
					return <li>
						<label>
							<input onClick={this.doneUndoneItem.bind(this, item)} id="remember" type="checkbox"
										 defaultChecked={item.done}/>
						</label>
						{this.state.selectedItem && this.state.selectedItem.id === item.id ?
							<input onChange={this.handleChange} className="edit-input" name="editedName" defaultValue={item.name}/> :
							<span style={doneStyle}>{item.name}</span>}
						<div className="item-icons-container">
							{this.state.selectedItem && this.state.selectedItem.id === item.id ?
								<img onClick={this.initEdit.bind(this, item, true)} src={SaveIcon}/> :
								<div>
									<img onClick={this.initEdit.bind(this, item, false)} src={EditIcon}/>
									<img onClick={this.deleteItemById.bind(this, item.id)} src={DeleteIcon}/>
								</div>}

						</div>
					</li>
				})}
			</ul>
		</selection>
	}

	doneUndoneItem(item) {
		item.done = !item.done;
		this.setState({
			items: this.state.items.map((i) => i.id === item.id ? item : i)
		});
		if(localStorage.getItem('user') !== 'dummy') {
			this.updateItemById(item);
		}
	}

	deleteItemById(itemId) {
		this.setState({
			selectedItem: null,
			items: this.state.items.filter(item => item.id !== itemId)
		});
		if(localStorage.getItem('user') !== 'dummy') {
			this.props.dispatch(deleteItemById(itemId));
		}
	}

	initEdit(item, isSave) {
		this.setState({
			selectedItem: isSave ? null : item
		});

		if (isSave) {
			item.name = this.state.editedName;
			console.log(item);
			if(localStorage.getItem('user') !== 'dummy') {
				this.updateItemById(item);
			}
		}
	}

	render() {
		return (
			<div className="todo-container">
				<div className="content">

					{this.props.listById.done || localStorage.getItem('user') === 'dummy' ? this.getHeader() : null}
					<div className="container">
						<div className="container-holder">
							{this.getAddInput()}
							{this.state.items ? this.showListOfItems() : null}
						</div>
					</div>
				</div>
			</div>
		)
	}
}

