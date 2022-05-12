Ext.application({
    name : 'Fiddle',

    launch : function() {
		//EmployeeID-14418
//Name-Anish Vijay Nair
//A scoreboard using extjs
//Scores are saved and winner displayed

// The data store containing the list of states

var Team=Ext.define('Team',{
    extend:'Ext.data.Model',
    fields:[
        {name: 'name',  type: 'string'},
        {name: 'run',   type: 'string'},
        {name: 'wicket', type:'string'},
        ],

    getrun: function() {

        return this.get('run');
    },

    getout:function(){
        return this.get('wicket');
    },
    setrun: function(n) {

        let cur=parseInt(this.get('run'));
        cur=cur+n;
        cur=toString(cur);

        this.set('run',cur);
    },

    setout:function(){
        let cur=parseInt(this.get('wicket'));
        cur=cur-1;
        cur=toString(cur);

        this.set('wicket',cur);
    }
})


let tm1=[0,0];
let tm2=[0,0];
let tm3=[0,0];


var states = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'name'],
    data: [{
        "abbr": "T1",
        "name": "Team1"
    }, {
        "abbr": "T2",
        "name": "Team2"
    }, {
        "abbr": "T3",
        "name": "Team3"
    }]
});


let ob1=Ext.create('Team',{
     name:"T1",
     run:"00",
     wicket:"00"


})

let ob2=Ext.create('Team',{
     name:"T2",
     run:"01",
     wicket:"00"


})

let ob3=Ext.create('Team',{
     name:"T3",
     run:"02",
     wicket:"00"

})


// Create the combo box, attached to the states data store
var combo = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'Combo Box',
    store: states,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'abbr',
    renderTo: Ext.getBody(),
    listeners:{

        change:function(e)
        {
          if(e.getValue()=='T1')
          {
             let val=ob1.getrun();
             let val1=ob1.getout();

             Ext.getCmp('run').setValue(tm1[0]);
             Ext.getCmp('out').setValue(tm1[1]);
          }
          else if(e.getValue()=='T2')
          {
             let val=ob2.getrun();
             let val1=ob2.getout();

             Ext.getCmp('run').setValue(tm2[0]);
             Ext.getCmp('out').setValue(tm2[1]);
          }

          else if(e.getValue()=='T3')
          {
            let val=ob3.getrun();
             let val1=ob3.getout();

             Ext.getCmp('run').setValue(tm3[0]);
             Ext.getCmp('out').setValue(tm3[1]);
          }

       }
    }



});


//main panel
Ext.create('Ext.panel.Panel', {
	fullscreen:true,
    renderTo: Ext.getBody(),
	minWidth: 700,
    minHeight: 600,
    //mwidth: 700,
    //height: 600,
    title: '<div style="text-align:center;">ScoreBoard</div>',

    layout: 'column',

    items: [{
        xtype: 'panel',
        title: '<div style="text-align:center;">Child1</div>',
        height: 600,
        columnWidth: 0.5,
        bodyStyle: 'margin-top: 5px;',

        items: [combo]
    }, {
        xtype: 'panel',
        title: '<div style="text-align:center;">Child2</div>',
        height: 600,
        columnWidth: 0.5,
        bodyStyle: 'margin-top: 5px;',

        items: [

            //Final Score
            {
                xtype: 'panel',
                title: 'Final Score',
                height: 100,
                layout: {
                    type: 'column'
                },

                defaults: {
                    flex: 1
                },
                items: [

                    {
                        xtype: 'displayfield',
                        fieldLabel: '<span style="color:green;"><b>RUN</b></span>',
                        id: 'run',
                        name: 'run',
                        value: '00',
                        columnWidth:0.5,


                        // fieldStyle: 'color: red;margin-right:10px',

                    }, {
                        xtype: 'displayfield',
                        fieldLabel: '<span style="color:red;"><b>WICKET</b></span>',
                        id: 'out',
                        name: 'wicket',
                        value: '00',
                        columnWidth:0.5
                    }
                ]
            },

            //Edit Score-panel
            {
                xtype: 'panel',
                title: 'Edit Score',
                height: 150,
                // layout: 'vbox',

                items: [

                    //radio button panel
                    {

                        //fieldcontainer for radios
                        xtype: 'fieldcontainer',
                        defaultType: 'radiofield',
                        defaults: {
                            flex: 1,

                        },

                        layout: 'hbox',

                        items: [{
                                boxLabel: '6',
                                name: 'score',
                                inputValue: '6',
                                id: 'radio6',
                                width: '50px'

                                // fieldStyle: 'color: red;margin-right:10px'
                            }, {
                                boxLabel: '4',
                                name: 'score',
                                inputValue: '4',
                                id: 'radio4',
                                width: '50px'
                            }, {
                                boxLabel: '3',
                                name: 'score',
                                inputValue: '3',
                                id: 'radio3',
                                width: '50px'
                            }, {
                                boxLabel: '2',
                                name: 'score',
                                inputValue: '2',
                                id: 'radio2',
                                width: '50px'
                            }, {
                                boxLabel: '1',
                                name: 'score',
                                inputValue: '1',
                                id: 'radio1',
                                width: '50px'
                            }

                        ]
                    }

                ],

                //functionality buttons
                bbar: [{
                        text: '<div style="color:white;">Score</div>',
                        width: 150,

                        style: {
                            'background-color': '#5fa2dd'

                        },
                        handler: function () {



                            if(combo.getValue()==null)
                             {
                                 Ext.Msg.alert('Enter Team');
                                 return;
                             }

                            if(tm1[1]==10 && tm2[1]==10 && tm3[1]==10){

                                let max=Math.max(tm1[0],tm2[0],tm3[0]);


                                if(tm1[0]==max)
                                 Ext.Msg.alert('Winner of the tournament is Team1');
                               else if(tm2[0]==max)
                                 Ext.Msg.alert('Winner of the tournament is Team2');
                               else if(tm3[0]==max)
                                 Ext.Msg.alert('Winner of the tournament is Team3');


                                 return;

                            }
                            var radio6 = Ext.getCmp('radio6'),
                                radio4 = Ext.getCmp('radio4'),
                                radio3 = Ext.getCmp('radio3'),
                                radio2 = Ext.getCmp('radio2'),
                                radio1 = Ext.getCmp('radio1');



                            d1 = Ext.getCmp('run');
                            d2 = Ext.getCmp('out');

                        //decision
                            let val=combo.getValue();

                            let ob;
                            if(val==='T1')
                                ob=tm1;

                            else if(val==='T2')
                            ob=tm2;
                            else
                              ob=tm3;

                         if(ob[1]!=10){



                            if (radio6.getValue()) {

                                ob[0]=ob[0]+6;
                                console.log(ob[0]);
                               d1.setValue(ob[0]);

                                return;
                            }

                            if (radio4.getValue()) {


                                ob[0]=ob[0]+4;
                                console.log(ob[0]);
                               d1.setValue(ob[0]);
                               return;

                            }
                            if (radio3.getValue()) {

                                ob[0]=ob[0]+3;
                                console.log(ob[0]);
                               d1.setValue(ob[0]);

                            }
                            if (radio2.getValue()) {

                                ob[0]=ob[0]+2;
                                console.log(ob[0]);
                               d1.setValue(ob[0]);
                               return;
                            }
                            if (radio1.getValue()) {

                                ob[0]=ob[0]+1;
                                console.log(ob[0]);
                               d1.setValue(ob[0]);
                               return;
                            }
                         }

                        }
                    },
                    '-', {
                        text: '<div style="color:white">Out</div>',
                        width: 150,

                        style: {
                            'background-color': '#5fa2dd',
                            'color': '#f5f5f5'

                        },
                        handler: function () {


                           if(combo.getValue()==null)
                             {
                                 Ext.Msg.alert('Enter Team');
                                 return;
                             }

                            d1 = Ext.getCmp('run');
                            d2 = Ext.getCmp('out');

                             //decision
                            let val=combo.getValue();

                            let ob;
                            if(val==='T1')
                                ob=tm1;

                            else if(val==='T2')
                            ob=tm2;

                            else
                              ob=tm3;


                            if (ob[1] <= 8) {

                                ob[1]=ob[1]+1;
                                d2.setValue(ob[1]);
                                return;

                            }
                            else if(ob[1]==9){

                                ob[1]=ob[1]+1;
                                d2.setValue(ob[1]);
                                console.log(ob[0]);
                               return;

                            }
                            else if(tm1[1]==10 && tm2[1]==10 && tm3[1]==10){

                                let max=Math.max(tm1[0],tm2[0],tm3[0]);


                                if(tm1[0]==max)
                                 Ext.Msg.alert('Winner of the tournament is Team1');
                               else if(tm2[0]==max)
                                 Ext.Msg.alert('Winner of the tournament is Team2');
                               else if(tm3[0]==max)
                                 Ext.Msg.alert('Winner of the tournament is Team3');


                                 return;

                            }

                        }
                    }

                ]

            }
        ]
    }]
});

	
	}
});