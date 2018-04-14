import axios from "axios"

import {LISTS_API} from "../constants/rest-api"
import {
	GET_LIST_BY_USER_ID_FAILED,
	GET_LIST_BY_USER_ID_SUCCESS,
	CREATE_LIST_SUCCESS,
	CREATE_LIST_FAILED,
	LIST_BY_ID_FAILED,
	LIST_BY_ID_SUCCESS,
	CREATE_ITEM_SUCCESS,
	CREATE_ITEM_FAILED,
	GET_ITEM_SUCCESS,
	GET_ITEM_FAILED,
	UPDATE_ITEM_SUCCESS,
	UPDATE_ITEM_FAILED,
	DELETE_ITEM_FAILED,
	DELETE_ITEM_SUCCESS,
	LIST_UPDATE_FAILED,
	LIST_UPDATE_SUCCESS,
	LIST_DELETE_FAILED,
	LIST_DELETE_SUCCESS
} from "../constants/list-constants"


export function getListsByUserId(user_id) {
	return function (dispatch) {
		return axios.get(LISTS_API + 'lists/user/' + user_id)
			.then((res) => {
				dispatch({type: GET_LIST_BY_USER_ID_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: GET_LIST_BY_USER_ID_FAILED, payload: err})
			})
	}
}

export function getListById(id) {
	return function (dispatch) {
		return axios.get(LISTS_API + 'list/id/' + id)
			.then((res) => {
				dispatch({type: LIST_BY_ID_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: LIST_BY_ID_FAILED, payload: err})
			})
	}
}


export function updateList(updateData, list_id) {
	return function (dispatch) {
		return axios.post(LISTS_API + 'list/update/' + list_id, updateData)
			.then((res) => {
				dispatch({type: LIST_UPDATE_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: LIST_UPDATE_FAILED, payload: err})
			})
	}
}

export function deleteList(list_id) {
	return function (dispatch) {
		return axios.delete(LISTS_API + 'list/delete/' + list_id)
			.then((res) => {
				dispatch({type: LIST_DELETE_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: LIST_DELETE_FAILED, payload: err})
			})
	}
}


export function createItem(item) {
	return function (dispatch) {
		return axios.post(LISTS_API + 'list/item/create', item)
			.then((res) => {
				dispatch({type: CREATE_ITEM_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: CREATE_ITEM_FAILED, payload: err})
			})
	}
}

export function getItemsByListId(list_id) {
	return function (dispatch) {
		return axios.get(LISTS_API + 'list/items/' + list_id)
			.then((res) => {
				dispatch({type: GET_ITEM_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: GET_ITEM_FAILED, payload: err})
			})
	}
}


export function updateItemById(updateData, itemId) {
	return function (dispatch) {
		return axios.post(LISTS_API + 'list/item/update/' + itemId, updateData)
			.then((res) => {
				dispatch({type: UPDATE_ITEM_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: UPDATE_ITEM_FAILED, payload: err})
			})
	}
}

export function deleteItemById(itemId) {
	return function (dispatch) {
		return axios.delete(LISTS_API + 'list/item/delete/' + itemId)
			.then((res) => {
				dispatch({type: DELETE_ITEM_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: DELETE_ITEM_FAILED, payload: err})
			})
	}
}

export function createList(reqParams) {
	return function (dispatch) {
		return axios.post(LISTS_API + 'list/create', reqParams)
			.then((res) => {
				dispatch({type: CREATE_LIST_SUCCESS, payload: res.data})
			})
			.catch((err) => {
				dispatch({type: CREATE_LIST_FAILED, payload: err})
			})
	}
}
