package app.viewex.core.details

interface ObjectMetadata<
        Name : ObjectName,
        Details : ObjectDetails> : Named<Name>, DetailsDefinition<Details> {
}
