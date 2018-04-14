import {
	GET_LIST_BY_USER_ID_SUCCESS,
	GET_LIST_BY_USER_ID_FAILED,
	CREATE_LIST_FAILED,
	CREATE_LIST_SUCCESS,
	LIST_BY_ID_SUCCESS,
	LIST_BY_ID_FAILED,
	CREATE_ITEM_SUCCESS,
	CREATE_ITEM_FAILED,
	GET_ITEM_SUCCESS,
	GET_ITEM_FAILED,
	UPDATE_ITEM_FAILED,
	UPDATE_ITEM_SUCCESS,
	DELETE_ITEM_SUCCESS,
	DELETE_ITEM_FAILED,
	LIST_UPDATE_SUCCESS,
	LIST_UPDATE_FAILED,
	LIST_DELETE_SUCCESS,
	LIST_DELETE_FAILED
} from "../constants/list-constants"

export function userById(state =
													 {
														 lists: null,
														 done: false
													 }
	, action) {
	switch (action.type) {
		case GET_LIST_BY_USER_ID_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case GET_LIST_BY_USER_ID_SUCCESS: {
			return {
				...state,
				done: true,
				lists: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}

export function createList(state =
														 {
															 list: null,
															 done: false
														 }
	, action) {
	switch (action.type) {
		case CREATE_LIST_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case CREATE_LIST_SUCCESS: {
			return {
				...state,
				done: true,
				list: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}

export function listById(state =
													 {
														 list: null,
														 done: false
													 }
	, action) {
	switch (action.type) {
		case LIST_BY_ID_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case LIST_BY_ID_SUCCESS: {
			return {
				...state,
				done: true,
				list: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}


export function deleteList(state =
													 {
														 list: null,
														 done: false
													 }
	, action) {
	switch (action.type) {
		case LIST_DELETE_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case 	LIST_DELETE_SUCCESS: {
			return {
				...state,
				done: true,
				list: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}

export function updateList(state =
													 {
														 list: null,
														 done: false
													 }
	, action) {
	switch (action.type) {
		case LIST_UPDATE_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case LIST_UPDATE_SUCCESS: {
			return {
				...state,
				done: true,
				list: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}



export function createItem(state =
													 {
														 item: null,
														 done: false
													 }
	, action) {
	switch (action.type) {
		case CREATE_ITEM_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case CREATE_ITEM_SUCCESS: {
			return {
				...state,
				done: true,
				item: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}


export function updateItem(state =
														 {
															 item: null,
															 done: false
														 }
	, action) {
	switch (action.type) {
		case UPDATE_ITEM_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case UPDATE_ITEM_SUCCESS: {
			return {
				...state,
				done: true,
				item: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}

export function deleteItem(state =
														 {
															 item: null,
															 done: false
														 }
	, action) {
	switch (action.type) {
		case DELETE_ITEM_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case DELETE_ITEM_SUCCESS: {
			return {
				...state,
				done: true,
				item: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}

export function items(state =
														 {
															 items: [],
															 done: false
														 }
	, action) {
	switch (action.type) {
		case GET_ITEM_FAILED: {
			return {
				...state,
				done: true
			}
		}
		case GET_ITEM_SUCCESS: {
			return {
				...state,
				done: true,
				items: action.payload
			}
		}
		default: {
			return {...state}
		}
	}
}


