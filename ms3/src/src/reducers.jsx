import {combineReducers} from "redux"
import {reg, login} from './reducers/auth-reducer'
import {userById, createList, listById, createItem, items, updateItem, deleteItem, updateList, deleteList} from './reducers/lists-reducer'

export default combineReducers({
	reg, login,
	userById, createList, listById, createItem, items, updateItem, deleteItem, updateList, deleteList
});
