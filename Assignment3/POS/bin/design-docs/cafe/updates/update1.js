function(doc, req){
    if (!doc){
        if ('id' in req && req['id']){
            // create new document
            return [{'_id': req['id']}, 'New World']
        }
        // change nothing in database
        return [null, 'Empty World']
    }

    doc['name'] = req.query.name;
    doc['amount'] = req.query.amount;
    // doc['edited_by'] = req['userCtx']['name']
    return [doc, 'Edited World!']

    // var newid = req.query.itemid;
    // // var newname = req.query.itemname;
    // // var newamount = req.query.itemamount;
    // // var message = 'set id to '+newid;
    // doc['id']=newid;
    // doc['name']=newname;
    // doc['amount']=newamount;

}