Ext.application({
    name : 'Fiddle',

    launch : function() {
	
var languageStore = Ext.create('Ext.data.Store', {
    fields: ['language', 'name'],
    data: [
    {
        "language": 1,
        "name": "English"
    }, 
    {
        "language": 2,
        "name": "Italian"
    }, 
    
    {
        "language": 3,
        "name": "Japanese"
    },
    {
        "language": "4",
        "name": "Mandarin"
    },
    {
        "language": "5",
        "name": "French"
    },
    {
        "language": "6",
        "name": "German"
    },
    {
        "language": "7",
        "name": "Mongolian"
    },
    
    ]
});

//stores created for combobox
// var specialStore = Ext.create('Ext.data.Store', {
//     fields: ['abbr', 'special'],
//     data : [
//         {"abbr":"ENG", "special":"Action"},
//         {"abbr":"HIN", "special":"Comedy"},
//         {"abbr":"FRE", "special":"Science Fiction"}
//     ]
// });
var specialStore = Ext.create('Ext.data.Store', {
    fields: ['abbr', 'special'],
    data : [
        {"abbr":"Trailers", "special":"Trailers"},
        {"abbr":"Commentaries", "special":"Commentaries"},
        {"abbr":"Deleted Scenes", "special":"Deleted Scenes"},
        {"abbr":"Behind the Scenes", "special":"Behind the Scenes"},
    ]
});
var ratingStore = Ext.create('Ext.data.Store', {
    fields: ['rating', 'rate'],
    data : [
        {"rating":"G", "rate":"G"},
        {"rating":"PG", "rate":"PG"},
        {"rating":"PG-13", "rate":"PG-13"},
        {"rating":"R", "rate":"R"},
        {"rating":"NC-17", "rate":"NC-17"},
    ]
});




//panels

var top=Ext.create('Ext.panel.Panel', {
            // renderTo: Ext.getBody(),
			border:false,
        
            height: 320,
            flex:1,
       

            title: 'Movie Advanced Search',
            layout: 'column',
            
            
          style:{
                'display':'flex',
                'flex-direction':'column',
                'justify-content':'center',
                 'align-items':'center'
             },
                
        
            
            

		
            items: [
                {
                xtype: 'form',
                
                columnWidth: 0.5,
				border:false,

                style: {

                    'margin-top':'50px',
                     'margin-left':'300px'

                },

                items:[
                       {
                          xtype:'textfield',
                          fieldLabel:'Movie Name',
                          name:'title',
                          id:'mov',
						border:false,

                       },{
                           xtype:'datefield',
                           fieldLabel:'Release Year',
                           id:'releases',
						   name:'release',
						   format: 'Y',
						   value:' ',
						   convert: function (value) {
                                 return value == ' ' ? null : new Date(value);
                                }
						   }
					
                       

                    ]
            }, {
                xtype: 'form',
                // title: 'Child Panel 2',

                height: 100,
                columnWidth: 0.5,
                border:false,
               
                style: {
                    'margin-top':'50px',
                     'margin-left':'300px'
                },
                items: [

                    {
                          xtype:'textfield',
                          fieldLabel:'Director Name',
                          name:'director',
                          id:'directors',
						
                    }
                   ,
                    {
                          xtype:'combobox',
                          fieldLabel: 'Language',
                          store: languageStore,
                          queryMode: 'local',
                          displayField: 'name',
                          valueField: 'language',
                          renderTo: Ext.getBody(),
                          id:'languages',
                          name:'language',
						  border:false,
                    }
                ],

            }],
           buttons:[


                    {
                     text:'<span style="color:white;">Search</span>',
                     id:'search',

                     style:{
                          
                         //'margin-right':'500px',
                         'background-color':'#5fa2dd',
                         'color':'#e4e4e4'
                        },
                        
                        handler:function(){
                            
                          
                            
                        var grid=Ext.getCmp('textGrid');
                        
                        var title=Ext.getCmp('mov').value;
                        var director=Ext.getCmp('directors').value;
                        var release=new Date(Ext.getCmp('releases').value);
                        var rel=release.getFullYear();
                        
                    
                        var language=Ext.getCmp('languages').value;
                 
					
					    if(isNaN(rel) || rel==1970){
                          rel=0;
                      }
                      
                      if(language==null){
                          language=0;
                      }
                      	var obj={"title":title,"director":director,"release":rel,"language":language}
					console.log(obj);
					
                       var myStore = Ext.create('Ext.data.Store', {
                           model: 'Movie',
                           pageSize:5,
                           autoLoad: {start: 0, limit: 5},
                           proxy: {
                               type: 'ajax',
                             
                                extraParams:obj,
                                 url: 'Search',
                                //url:'https://api.npoint.io/fe5d872d9fe87e4d356f',
                                reader: {
                                        type: 'json',
             							rootProperty: 'list',
             							totalProperty:'total',
										 successProperty:'success',
                                       }
                               },
                             storeId:'dataStore1',
     
                           });

						
				 		grid.setStore(myStore);
					
                        // movieStore1.reload();
                        //console.log(grid.store);
                        
                        
                            
                      }

                    },
                    {
                    text:'<span style="color:white;">Reset</span>',
                     id:'reset',

                     style:{
				 	     'margin-right':'700px',
                         'background-color':'#5fa2dd',
                         'color':'#e4e4e4'
                     }
                    },

                  ]
            });



//model
Ext.define('Movie', {
    extend: 'Ext.data.Model',
    fields: [ 'id','title', 'description', 'release','language','director','rating','special' ]

});


//store
var movieStore = Ext.create('Ext.data.Store', {
	autoLoad: {start: 0, limit:8},
// 	autoLoad:true,
    model: 'Movie',
    pageSize:8,
     proxy: {
         type: 'ajax',
          url: 'Fetch',	
       //     url:'https://api.npoint.io/05eca8c2088c8c49c392',
		
         reader: {
             type: 'json',
             rootProperty: 'list',
             totalProperty:'total',
			 successProperty:'success',
         }
     },
     
});




//addpanel

var addpanel = Ext.create('Ext.form.Panel', {

    url:'Add',
    defaults:{
        width:380,
        style:{
            'padding': '10px 20px',
        }

    },
    
    items: [{

        xtype: 'textfield',
        fieldLabel: 'Title',
        name: 'title',
        // itemId:'title',
        id:'title',
        allowBlank:false

       }, {
        xtype: 'numberfield',
        name: 'release',
        fieldLabel: 'Release Year',
        // itemId:'release',
        id:'release',
        allowBlank:false,
        

        // Set step so it skips every other number
        step: 1,
        value: 0,

     },
      {
        xtype: 'combobox',
        fieldLabel: 'Special Features',
        name: 'special',
        // itemId:'special',
        id:'special',
        allowBlank:false,

        store: specialStore,
        queryMode: 'local',
        displayField: 'special',
        valueField: 'abbr',
      },
      {
        xtype: 'combobox',
        fieldLabel: 'Rating',
        name: 'rating',
        // itemId:'rating',
        id:'rating',
        allowBlank:false,

        store: ratingStore,
        queryMode: 'local',
        displayField: 'rate',
        valueField: 'rating',
      },
      {
        xtype: 'combobox',
        fieldLabel: 'Language',
        name: 'language',
        // itemId:'language',
        id:'language',
        allowBlank:false,

        store: languageStore,
        queryMode: 'local',
        displayField: 'name',
        valueField: 'language',
      },
      {
        xtype: 'textfield',
        fieldLabel: 'Director Name',
        name: 'director',
        id:'director',
        allowBlank:true
      },
       {
            xtype: 'textarea',
            fieldLabel: 'Description',
            name:'description',
            id: 'description',
            allowBlank:true
        },
       ],
        
    buttons:[

            {
             text:"<span style='color:white;'>Save</span>",
              
			  handler: function() {
                var form = this.up('form'); // get the form panel
                if (form.isValid()) { // make sure the form contains valid data before submitting
                    form.submit(
                         {
                         failure: function(form, action) {
                           Ext.Msg.alert('Success',"Submitted");
							window.location.reload();
                         },
                         success: function(form, action) {
                             Ext.Msg.alert('Submitted');
							 window.location.reload();

                         }
                        }
                    );
                    
                   // Ext.Msg.alert('Successfully Submitted');

                    
                   // addpanel.reset();
                   // Addpanel.hide();
                    
                } else { // display error alert if the data is invalid
                    Ext.Msg.alert('Invalid Data', 'Please correct form errors.')
                }
             }
            },
            
            {
             text:"<span style='color:white;'>Cancel</span>",
             style:{
                 'margin-right':'200px'
            },
             
             handler:function(){
                 Addpanel.hide();
             }
           }

         ]
});
var Addpanel=Ext.create('Ext.window.Window',{
   
   title: 'Add Film',
   height: 580,
    width: 500,
    modal:true,
    closeAction:'hide',
    
    renderTo: Ext.getBody(),
    id:'addpanel',
    
    
    items:[addpanel]
    
});



var editpanel = Ext.create('Ext.form.Panel', {


    url:'Edit',
    id:'editpanel',
     height: 580,
    width: 500,
    defaults:{
        width:380,
        style:{
            'padding': '10px 20px',
        }

    },

    items: [
        

     {

        xtype: 'textfield',
        fieldLabel: 'Title',
        name: 'title',
        id:'title1',
        allowBlank:false,

    }, {
        xtype: 'numberfield',
        name: 'release',
        fieldLabel: 'Release Year',
        id:'release1',
        allowBlank:false,

        // Set step so it skips every other number
        step: 1,
        value: 0,

     },
      {
        xtype: 'combobox',
        fieldLabel: 'Special Features',
        name: 'special',
        id:'special1',
        allowBlank:false,

        store: specialStore,
        queryMode: 'local',
        displayField: 'special',
        valueField: 'abbr',
      },
      {
        xtype: 'combobox',
        fieldLabel: 'Rating',
        name: 'rating',
        id:'rating1',
        allowBlank:false,

        store: ratingStore,
        queryMode: 'local',
        displayField: 'rate',
        valueField: 'rating',
      },
      {
        xtype: 'combobox',
        fieldLabel: 'Language',
        name: 'language',
        allowBlank:false,
       
        id:'language1',
        store: languageStore,
        queryMode: 'local',
        displayField: 'name',
        valueField: 'language',
      },
      {
        xtype: 'textfield',
        fieldLabel: 'Director Name',
        name: 'director',
        id:'director1',
        allowBlank:true,
      },
        {
            xtype: 'textarea',
            fieldLabel: 'Description',
            name:'description',
            id: 'description1',
            allowBlank:false,
        },
        
      {
        xtype: 'textfield',
        // fieldLabel: 'id',
        name: 'id',
        id:'id1',
        allowBlank:false,
        width:100,
		style:{
			'visibility':'hidden'
		}
      },
        
        
        
      ],

       buttons:[
            {
             text:"<span style='color:white;'>Save</span>",
             
              handler: function() {
				var grid=Ext.getCmp('textGrid');
                var form = this.up('form'); // get the form panel
                if (form.isValid()) { // make sure the form contains valid data before submitting
                    form.submit({
                        success: function(form, action) {
                            Editpanel.hide();
							grid.setStore(movieStore);
							movieStore.reload();
							
							Ext.toast({
                                 html: 'Data Saved Sucessfully',
                                 width: 200,
                                 align: 'br'
                             });	
						   
                        },
                        failure: function(form, action) {
						//	movieStore.load({url:'http://localhost:8080/Summer_Internship_Backend/Fetch'});
							Editpanel.hide();
							grid.setStore(movieStore);
							movieStore.reload();
							
							Ext.toast({
                                 html: 'Data Saved Sucessfully',
                                 width: 200,
                                 align: 'br'
                             });	
							
                        }
                    });
                } else { // display error alert if the data is invalid
                    Ext.Msg.alert('Invalid Data', 'Please correct form errors.')
                }
				
				//movieStore.load({url:'http://localhost:8080/Summer_Internship_Backend/Fetch'})
				
             }
            },
            
            {
             
             text:"<span style='color:white;'>Cancel</span>",
             style:{
                 'margin-right':'200px',
               },


             handler:function(){
                 Editpanel.hide();

               }
           },
        ]
        
});

var Editpanel=Ext.create('Ext.window.Window',{
   
   title: 'Edit Film',
   
    modal:true,
    closeAction:'hide',
    renderTo: Ext.getBody(),
    id:'Editpanel',

    
    items:[editpanel]
})


  var deletepanel = Ext.create('Ext.form.Panel', {

    url:'Delete',
     height: 150,
    width: 500,
    defaults:{
        width:380,
        style:{
            'padding': '10px 20px',
        }

    },
    id:'deletepanel',
    items: [

        {
          xtype:'component',
          html:'<span>Are you sure you want to delete these items?</span>',

         style:{
             'font-size':'18px',
             'padding-top':'10px',
         }
        },
        {

        xtype: 'textfield',
        fieldLabel: 'Title',
        name: 'id',
        // itemId:'title',
        id:'title3',
        style:{
             'visibility':'hidden',
            'padding':'5px'

        },

        }

      ],

    buttons:[

            {
             text:"<span style='color:white;'>Yes</span>",

			  handler: function() {
			     
			    var grid=Ext.getCmp('textGrid');
                var form = this.up('form'); // get the form panel
                if (form.isValid()) { // make sure the form contains valid data before submitting
                    form.submit(
                         {
                         failure: function(form, action) {
                         //  Ext.Msg.alert('Submitted');
                            Deletepanel.hide(),
                            
                             grid.setStore(movieStore);
							movieStore.reload(),
                            
							
							// movieStore.load({url:'http://localhost:8080/Summer_Internship_Backend/Fetch'})
                           //  movieStore.load({url:'https://api.npoint.io/05eca8c2088c8c49c392'})
                               Ext.toast({
                                 html: 'Data Saved Sucessfully',
                                 width: 200,
                                 align: 'br'
                             });							 
							 //window.location.reload();
							
                         },
                         success: function(form, action) {
                            
                            Deletepanel.hide(),
                             
                            
                            grid.setStore(movieStore);
                             movieStore.reload(),
							
							// movieStore.load({url:'http://localhost:8080/Summer_Internship_Backend/Fetch'})
                           //  movieStore.load({url:'https://api.npoint.io/05eca8c2088c8c49c392'})
                        
                               Ext.toast({
                                 html: 'Data Saved Sucessfully',
                                 width: 200,
                                 align: 'br'
                             });							 
							 

                         }
                        }
                    );

                    // setTimeout(()=>{
                    //     window.location.reload();
                        
                    // },4000)
                } else { // display error alert if the data is invalid
                    Ext.Msg.alert('Invalid Data', 'Please correct form errors.')
                }
                
                
             }
            },

            {
             text:"<span style='color:white;'>Cancel</span>",
             style:{
                 'margin-right':'200px'
               },

             handler:function(){
                 Deletepanel.hide();
             }
           }

         ]
});
var Deletepanel=Ext.create('Ext.window.Window',{

   title: 'Delete Film',

    modal:true,
    closeAction:'hide',

    renderTo: Ext.getBody(),
    id:'deletePanel',

    items:[deletepanel]

 });


var bottom=Ext.create('Ext.grid.Panel', {

    store: movieStore,
    
    height: 400,
   // width:780,
    flex:1,
   
    title:'Movie Grid', 
    // selType: 'rowmodel',
    // selType:'cellmodel',
    
	dockedItems:[
	    {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        store:movieStore,
        // displayMsg: 'Displaying topics {0} - {1} of {2}',
        // emptyMsg: "No topics to display",

         items: [
             '-', 
            {
             xtype:'button',
             text:'Add',
             iconCls: 'fa fa-plus-circle',
             style:{
                'border':'2px solid #80808040',
                'background-color':'#80808040'
                 
             },
             handler:function(){
                Addpanel.show();
                return;

             }
             
             
         },
         '-',
         {
             xtype:'button',
             text:'Edit',
             iconCls: 'fa fa-edit',
             id:'editbtn',
             style:{
                'border':'2px solid #80808040',
                'background-color':'#80808040'
                 
             },
             handler:function(){
                
                 var rec=Ext.getCmp('textGrid').getSelectionModel().getSelected();
                 
                 if(rec.items.length>1 || rec.items.length==0){
                //   Ext.get('editbtn').setDisabled(true);
                     Ext.Msg.alert('Oops!','Edit can be done on one item at a time');
                 }
                 else{
                
                   
                   
                  var ide=rec.items[0].data.id;
                  var tit=rec.items[0].data.title;
                  var rel=rec.items[0].data.release;
                  var spec=rec.items[0].data.special;
                  var rat=rec.items[0].data.rating;
                  var lan=rec.items[0].data.language;
                  var dir=rec.items[0].data.director;
                  var des=rec.items[0].data.description;
                  
                 temp=languageStore.data.items;
                 
                 var key;
                 temp.forEach((obj)=>{
                     if(obj.data.name==lan)
                     {
                         key=obj.data.language;
                         console.log(key);
                     }
                 });
                  
                  
                  console.log(ide);
                  
                  Ext.getCmp('id1').setValue(ide);
                  Ext.getCmp('title1').setValue(tit);
                  Ext.getCmp('release1').setValue(rel);
                  Ext.getCmp('special1').setValue(spec);
                  Ext.getCmp('rating1').setValue(rat);
                  Ext.getCmp('language1').setValue(key);
                  Ext.getCmp('director1').setValue(dir);
                  Ext.getCmp('description1').setValue(des);
                  
                 Editpanel.show();
                 }
                 
                 return;
             }
             
         },
         '-',
         {
             xtype:'button',
             text:'Delete',
             style:{
                'border':'2px solid #80808040',
                'background-color':'#80808040'
                 
             },
             
             handler:function(){
                  var rec=Ext.getCmp('textGrid').getSelectionModel().getSelected();
                 
                //  console.log(rec.items);
                 
                 
                 
                 if(rec.items.length==0){
                //   Ext.get('editbtn').setDisabled(true);
                     Ext.Msg.alert('Oops!','Delete can be done on atleast one item at a time');
                 }
                 
                 else{
                     
                     var len=rec.items.length;
                 
                    var tit="";
                 
                     rec.items.forEach((obj)=>{
                         tit=tit+obj.data.id+","
                    })
                 
                 tit=tit.slice(0,-1);
                 console.log(tit)
                 
                
                     Ext.getCmp('title3').setValue(tit);
                
                     Deletepanel.show();
                 }
                 
                 return;
             }
             
         }
         ]
	     
	 }
    ],
    columns: [
        
        {
            text:'FilmID',
            flex:1,
            sortable:true,
            hidden:true,
            dataIndex:'id',
            
        },
        {
            text: 'Title',
            flex:1,
            sortable: false,
            hideable: false,
            dataIndex: 'title'
        },
        {
            text: 'Description',
            flex:1,
            dataIndex: 'description',
            hidden: false
        },
        {
            text: 'Release',
            flex: 1,
            dataIndex: 'release',
            hidden:false
        },
        {
            text: 'Language',
            flex: 1,
            dataIndex: 'language',
            hidden:false,
        },
        {
            text: 'Director',
            flex: 1,
            dataIndex: 'director',
            hidden:false,
        },
        {
            text: 'Rating',
            flex: 1,
            dataIndex: 'rating',
            hidden:false,
        },
        {
            text: 'Special Features',
            flex: 1,
            dataIndex: 'special',
            hidden:false,
        },
    ],
    id:'textGrid',
    selModel:{
        injectCheckbox:'first',
        checkOnly:'true',
        model:'SIMPLE',
        type:'checkboxmodel'
    }
});

// Viewport Panel
// var mainPanel=Ext.create('Ext.container.Viewport',{

//       fullscreen: true,

//     defaults: {
//         flex: 1
//     },
        
// })

// mainPanel.add(top);
// mainPanel.add(bottom);

var mainPanel=Ext.create('Ext.container.Container',{

      fullscreen: true,
    // width:700,
      renderTo:Ext.getBody(),

    defaults: {
        flex: 1
    },
        
})

mainPanel.add(top);
mainPanel.add(bottom);
	  
     console.log(movieStore.data.items);
        
    }
    
   
});
