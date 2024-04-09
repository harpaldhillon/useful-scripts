pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                script {
                    // Run unit test
                    testReplaceForwardSlash()
                }
            }
        }
    }
}

def replaceForwardSlash(String jsonString) {
    // Parse the JSON string into a Groovy map
    def json = readJSON text: jsonString
    
    // Get the value of the "image_name" attribute and replace forward slashes with hyphens
    def imageName = json.image_name.replaceAll('/', '-')
    
    return imageName
}

// Unit test
def testReplaceForwardSlash() {
    def jsonString = '{"image_name":"path/to/image.png", "other_attribute":"value"}'
    def expectedJsonString = '{"image_name": "path-to-image.png", "other_attribute": "value"}'
    assert replaceForwardSlash(jsonString) == "path-to-image.png"
}
