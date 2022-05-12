Ext.application({
    name : 'Fiddle',

    launch : function() {
var filterPanel = Ext.create('Ext.form.Panel', {
    bodyPadding: 5, // Don't want content to crunch against the borders
    width: 500,
    // title: 'Form Data',
    defaultType: 'textfield',
    url:'Add',

    items: [

        {
            // xtype:'textfield',
            fieldLabel: 'First Name',
            name: 'firstName',

        }, {

            fieldLabel: 'Last Name',
            name: 'lastname',
            msgTarget: 'under', // location of the error message
            invalidText: '"{0}" bad. "{1}" good.' // custom error message text
        }

    ],
    buttons: [


          {
            text: 'Submit',
            style:{
                'margin-right':'250px'
            },
            handler: function() {
                var form = this.up('form'); // get the form panel
                if (form.isValid()) { // make sure the form contains valid data before submitting
                    form.submit({
                        success: function(form, action) {
                          Ext.Msg.alert('Success');
                        },
                        failure: function(form, action) {
                            Ext.Msg.alert('Failed');
                        }
                    });
                } else { // display error alert if the data is invalid
                    Ext.Msg.alert('Invalid Data', 'Please correct form errors.')
                }
            }


            // handler:function(){
            //     var form=this.up('form');
            //     console.warn('Print the data',form);
            //     form.submit();
            // }
          }
       ]


});

var mainPanel=Ext.create('Ext.window.Window',{
     renderTo:Ext.getBody(),
     modal:true,
     closeAction:'hide',
     title:'Form Data',
    //  width:500,
    //  height:300,
     
     items:[filterPanel]
}).show();
    }
});