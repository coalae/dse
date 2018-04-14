module.exports = function (router) {
    /**
     *
     */
    let mysql = require('mysql');

    /**
     *
     * @type {Connection}
     */

    let connection = mysql.createConnection({
        host: 'a1263502.mysql.univie.ac.at',
        user: 'a1263502',
        password: 'kay2devi',
        database: 'a1263502',
        multipleStatements: true
    });

    /**
     *
     */

    connection.connect(function (err) {
        if (err) {
            console.error('error connecting: ' + err.stack);
            return;
        }
        console.log('connected as id ' + connection.threadId);
    });

    /**
     * Create Category
     * A category can beimportant, not important or very important.
     */

    router.post('/category/create', function (req, res) {

        let category = {
            name: req.body.name,
        };

        connection.query('INSERT INTO category SET ?', category, function (err, result) {
            if (err) throw err;
            res.send(JSON.stringify(result));
        });

    });

    /**
     * Create List( id, name, created, category_id).
     * Every List must have a specific category (important, not important, very important)
     */

    router.post('/list/create', function (req, res) {

        let list = {
            user_id: req.body.user_id,
            name: req.body.name,
            created: new Date(),
            category_id: req.body.category_id
        };

        connection.query('INSERT INTO list SET ?', list, function (err, result) {
            if (err) throw err;
            res.send(JSON.stringify(result));
        });

    });

    /**
     * Create Item (name, done, list_id).
     * A list consists of one or many items.
     */

    router.post('/list/item/create', function (req, res) {

        let item = {
            name: req.body.name,
            done: req.body.done,
            list_id: req.body.list_id
        };

        connection.query('INSERT INTO item SET ?', item, function (err, result) {
            if (err) throw err;
            res.send(JSON.stringify(result));
        });

    });

    /**
     * This method shows us all the lists in the database
     */

    router.get('/lists', function (req, res) {

        let query = 'SELECT * FROM list';
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });

    /**
     * THis method shows us all the lists depending on the category Id
     *
     */

    router.get('/lists/category/:category_id', function (req, res) {
        let query = 'SELECT * FROM list INNER JOIN category ON list.category_id=category.id WHERE category.id=' + req.params.category_id;
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });

    /**
     * THis method shows us all the lists depending on the user Id
     */

    router.get('/lists/user/:user_id', function (req, res) {
        let query = 'SELECT todo.name, todo.id, todo.created, cat.name as catname, cat.id as catid FROM list as todo INNER JOIN category as cat ON todo.category_id=cat.id WHERE todo.user_id=' + req.params.user_id;
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });

    /**
     * This method shows us the list depending on the list Id
     */

    router.get('/list/id/:list_id', function (req, res) {
        let query = 'SELECT todo.name, todo.id, todo.created, cat.name as catname FROM list as todo INNER JOIN category as cat ON todo.category_id=cat.id WHERE todo.id=' + req.params.list_id;
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });


    /**
     * This method shows us the list depending on the name.
     */

    router.get('/list/name/:list_name', function (req, res) {
        let query = "SELECT * FROM list WHERE name='" + req.params.list_name + "'";
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });

    /**
     * This method shows us all the categories in our database
     */

    router.get('/categories', function (req, res) {
        let query = 'SELECT * FROM category';
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });

    /**
     * Show Items by List Id
     */

    router.get('/list/items/:list_id', function (req, res) {
        let query = 'SELECT item.id, item.name, item.done, item.list_id FROM item INNER JOIN list ON list.id=item.list_id WHERE list.id=' + req.params.list_id;
        connection.query(query, function (error, results, fields) {
            if (error) throw error;
            res.send(JSON.stringify(results));
        });
    });

    /**
     * Update Item (name, done) by Item Id
     */

    router.post('/list/item/update/:item_id', function (req, res) {
        let item = [req.body.name, req.body.done, req.body.list_id, req.params.item_id];

        let sql = "UPDATE item SET name =?, done=?, list_id=?  WHERE id = ?";

        connection.query(sql, item, function (err, result) {
            if (err) {
                throw err;
            }
            res.send(JSON.stringify(result));
        });
    });

    /**
     * Update List(name) by List Id
     */

    router.post('/list/update/:list_id', function (req, res) {
        let list = [req.body.name, req.params.list_id];

        let sql = "UPDATE list SET name =? WHERE id = ?";

        connection.query(sql,list, function (err, result) {
            if (err) {
                throw err;
            }
            res.send(JSON.stringify(result));
        });
    });


    /**
     * Delete Item from List
     */

    router.delete('/list/item/delete/:item_id', function (req, res) {
        connection.query('DELETE FROM item WHERE id= ?', [req.params.item_id], function (err, result) {
            if (err) {
                throw err;
            }
            res.send(JSON.stringify(result));
        });
    });

    /**
     * Delete List
     * Delete also all items of the List
     * Changed in MySQL - Item on delete cascade
     */

    router.delete('/list/delete/:list_id', function (req, res) {
        connection.query('DELETE FROM list WHERE id= ?', [req.params.list_id], function (err, result) {
            if (err) {
                throw err;
            }
            res.send(JSON.stringify(result));
        });

    });


    /**
     * Methode completed fuer Item
     */

    router.post('/list/item/complete/:item_id', function (req, res) {

        connection.query('UPDATE item SET done=1 WHERE id = ?', [req.params.item_id], function (err, result) {
            if (err) {
                throw err;
            }
            res.send(JSON.stringify(result));
        });
    });
};