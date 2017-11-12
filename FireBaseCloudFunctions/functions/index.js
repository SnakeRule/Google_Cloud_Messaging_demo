// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);


// Take the text parameter passed to this HTTP endpoint and insert it into the
// Realtime Database under the path /messages/:pushId/original
exports.addMessage = functions.https.onRequest((req, res) => {
  // Grab the text parameter.
  const original = req.query.text;
  // Push the new message into the Realtime Database using the Firebase Admin SDK.
  admin.database().ref('/messages').push({original: original}).then(snapshot => {
    // Redirect with 303 SEE OTHER to the URL of the pushed object in the Firebase console.
    res.redirect(303, snapshot.ref);
  });
});


exports.sendNotification = functions.database.ref('/messages/{messageId}')
        .onWrite(event => {

        // Grab the current value of what was written to the Realtime Database.
        var eventSnapshot = event.data;
        var str1 = "Author is ";
        var str = str1.concat(eventSnapshot.child("author").val());
        console.log(str);

        if(eventSnapshot.child("topic").val() != "")
        {
        var topic = eventSnapshot.child("topic").val()
        }
        else
        {
        var topic = "asd";
        }
        var payload = {
            data: {
                title: eventSnapshot.child("message").val(),
                author: eventSnapshot.child("author").val(),
                token: eventSnapshot.child("token").val()
            }
        };

        // Send a message to devices subscribed to the provided topic.
                return admin.messaging().sendToTopic(topic, payload)
                    .then(function (response) {
                        // See the MessagingTopicResponse reference documentation for the
                        // contents of response.
                        console.log("Successfully sent message:", response);
                    })
                    .catch(function (error) {
                        console.log("Error sending message:", error);
                    });
                });