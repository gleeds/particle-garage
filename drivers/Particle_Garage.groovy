preferences {
    input name: "deviceId", type: "text", title: "Device ID", required: "True"
    input name: "token", type: "password", title: "Access Token", required: "True"
}

metadata {
    definition (
        name: "Particle Garage Door", 
		namespace: "particle-gd", 
		author: "gleeds@gmail.com", 
		importUrl: "https://raw.githubusercontent.com/gleeds/particle-garage/master/drivers/Particle_Garage.groovy"
    ) {
        capability "GarageDoorControl"
    }
}

def open() {

}

def close() {

}

def poll() {
    checkStatus()
}

def checkStatus() {

    httpGet(uri: "https://api.particle.io/v1/devices/${deviceId}/Live_Status?access_token=${token}",
    	contentType: 'application/json',)
    {resp ->           
            log.debug "resp data: ${resp.data}"
            log.debug "result: ${resp.data.result}"
		sendEvent(name: "door", value: res.data.result == 1 ? "closed":"open" )
	}

}