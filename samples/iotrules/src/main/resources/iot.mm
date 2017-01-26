class org.mwg.plugins.rules.samples.iotrules.Room {
    att name: String

    rel devices : org.mwg.plugins.rules.samples.iotrules.Devices {
        with opposite "room"
    }
}


index idx_room: org.mwg.plugins.rules.samples.iotrules.Room {
    name
}

class org.mwg.plugins.rules.samples.iotrules.Devices {
    att available: Boolean
    att homeId: String
    att id: String
    att name: String
    att pictureUrl : String

    rel technology : org.mwg.plugins.rules.samples.iotrules.Technology {
        with opposite "devices"
    }

    rel room : org.mwg.plugins.rules.samples.iotrules.Room {
        with opposite "devices"
    }

    rel parameters: org.mwg.plugins.rules.samples.iotrules.Parameters
}

class org.mwg.plugins.rules.samples.iotrules.Parameters {
    att name: String
    att name: String
}

class org.mwg.plugins.rules.samples.iotrules.Technology {
    att name: String

    rel devices : org.mwg.plugins.rules.samples.iotrules.Devices {
        with opposite "technology"
    }
}

index idx_technology: org.mwg.plugins.rules.samples.iotrules.Technology {
    name
}
