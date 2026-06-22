package com.example.e_ticketinghelpdeskuts.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.e_ticketinghelpdeskuts.R

/**
 * Branded app logo: the e-ticket mark inside a circular badge.
 * Reused on the splash screen and the auth screens for a consistent identity.
 */
@Composable
fun BrandLogo(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    badgeColor: Color = MaterialTheme.colorScheme.onPrimary,
    markColor: Color = MaterialTheme.colorScheme.primary,
    elevation: Dp = 6.dp
) {
    Surface(
        modifier = modifier.size(size),
        shape = CircleShape,
        color = badgeColor,
        shadowElevation = elevation
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.ic_brand_logo),
                contentDescription = "Logo E-Ticketing Helpdesk",
                tint = markColor,
                modifier = Modifier.size(size * 0.55f)
            )
        }
    }
}
