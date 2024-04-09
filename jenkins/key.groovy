pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Generate Key'
                
                script {
                    // Example usage:
                    def jsonRecord = '{"name": "harpaldhillon/test/alpine", "id1": "12345", "tag": "1.1", "id2": "9631", "other_attribute": "value"}'
                    def modifiedJson = replaceForwardSlash(jsonRecord)
                    println modifiedJson
                    println modifiedJson.length()
                }
            }
        }
    }
}


def replaceForwardSlash(String jsonString) {
    // Parse the JSON string into a Groovy map
    def json = readJSON text: jsonString
    
    // Get the value of the "image_name" attribute and replace forward slashes with hyphens
    def recordKey = json.name.replaceAll('/', '-') + '-' + json.id1 + '-' + json.id2
    
    
    // Return the record key value
    return recordKey
}
