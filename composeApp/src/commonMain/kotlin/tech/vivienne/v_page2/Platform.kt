package tech.vivienne.v_page2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform